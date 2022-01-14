package Store;

import General.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Model of Store
 */
public class Store {

    /**
     * The Customer using the store
     */
    private Customer customer;

    /**
     * List of views associated with Store
     */
    private List<StoreView> views;

    /**
     * The inventory of the store
     */
    private ProductStockCollection inventory;

    /**
     * Default Constructor
     */
    public Store()
    {
        customer = null;
        views = new ArrayList<>();
        inventory = new ProductStockMap();
    }

    /**
     * Sets the Customer
     *
     * @param customer              Customer, the customer using the store
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Adds a view to list of views associated with Store model
     *
     * @param newView       StoreView, the new view associated with Store
     */
    public void addView(StoreView newView) {
        views.add(newView);
    }

    /**
     * Removes a view from the list of views associated with Store model
     *
     * @param oldView       StoreView, the view to be removed
     */
    public void removeView(StoreView oldView) {
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).equals(oldView)) {
                views.remove(i);
                break;
            }
        }
    }

    /**
     * Sets the inventory with another inventory
     *
     * @param inventory     ProductStockCollection, the new inventory
     */
    public void setInventory(ProductStockCollection inventory) {
        this.inventory = inventory;
    }

    /**
     * Sets the inventory with no sorting and sends the inventory to all the views
     */
    public void setInventory() {

       getInventoryFromDb("select * from products");
       setGUIProducts();
    }

    /**
     * Sets the inventory with specified sorting and send the inventory to all the views
     *
     * @param selectionChoice       String, sql statement to sort the inventory
     */
    public void setInventory(String selectionChoice)
    {
        getInventoryFromDb(selectionChoice);
        setGUIProducts();
    }

    /**
     * Gets the data from the products table with a specified sort sql statement and sets the inventory with itw
     *
     * @param selectionChoice       String, sql statement to sort the inventory
     */
    private void getInventoryFromDb(String selectionChoice)
    {
        inventory = new ProductStockMap();
        Connection connection = DatabaseConnection.getConnection();

        try
        {
            PreparedStatement statement = connection.prepareStatement(selectionChoice);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {

                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("name");
                double productPrice = resultSet.getDouble("price");
                String productImg = resultSet.getString("img");
                int productStock = resultSet.getInt("stock");

                Product product = new Product(productId,productName,productPrice,productImg);

                productStock = productStock - customer.getProductStock(product);
                inventory.add(product, productStock);

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
    }

    /**
     * Sets the products in the GUI to display for all views
     */
    private void setGUIProducts()
    {
        for (StoreView v:views)
        {
            v.addProductsToGUI(inventory,customer.getShoppingCart());
        }
    }

    /**
     * Customer adds one to the stock of a product in their shopping cart. One stock is removed for the product in
     * the inventory
     *
     * @param product       Product, the product specified
     */
    public void customerAddProduct(Product product)
    {
        customer.addToCart(product, 1);
        inventory.remove(product, 1);

        int stockLeft = inventory.getProductStock(product);
        boolean addEnabel = (stockLeft==0)?false:true;
        boolean removeEnabel = true;

        updateViewCart(product, addEnabel,removeEnabel);

    }

    /**
     * Customer removes one from the stock of a product in their shopping cart. One stock is added for the product in
     * the inventory
     *
     * @param product       Product, the product specified
     */
    public void customerRemoveProduct(Product product)
    {
        customer.removeFromCart(product,1);
        inventory.add(product, 1);

        boolean addEnabel = true;
        boolean removeEnabel = customer.contains(product);

        updateViewCart(product, addEnabel,removeEnabel);
    }

    /**
     * Update all the views after a product is added or removed to customer shopping cart
     *
     * @param product               Product, the product added or removed to shopping cart
     * @param addEnable             boolean, true if add button should be enabled; false otherwise
     * @param removeEnable          boolean, true if remove button should be enabled; false otherwise
     */
    private void updateViewCart(Product product, boolean addEnable, boolean removeEnable)
    {
        for (StoreView v:views)
        {
           v.updateCustomerCart(product, addEnable, removeEnable);
        }
    }

    /**
     * Turns the Customer shopping cart into a String
     *
     * @return                  String, the shopping cart in text version
     */
    private String turnCartToText()
    {
        StringBuilder cart = new StringBuilder();
        Collection<Product> cartProducts = customer.getAllProducts();

        if(cartProducts.size()>0)
        {

            for(Product product: customer.getAllProducts())
            {
                String itemName = product.getName();
                int qty = customer.getProductStock(product);
                double price = product.getPrice();
                cart.append(itemName);
                cart.append("  QTY:");
                cart.append(qty);
                cart.append("  PRICE:$");
                cart.append(price);
                cart.append("\n");
            }
            double total = customer.getTotalCost();

            cart.append("\n");
            cart.append("Total: $");
            cart.append(total);
        }
        else
        {
            cart.append("The Shopping Cart is Empty");
        }

        return cart.toString();
    }

    /**
     * Gets the text version of the shopping card and tells all the views to display the cart
     */
    public void getCustomerCartInText()
    {
        String cartText = turnCartToText();

        for (StoreView v:views)
        {
            v.displayCustomerCart(cartText);
        }
    }

    /**
     * Checks if the shopping cart is not empty and will createOrder, otherwise wil call checkoutFailed
     */
    public void customerCheckout()
    {
        if(customer.getAllProducts().size()>0)
        {
            createOrder();
        }
        else
        {
            checkoutFailed("No Items in Cart");
        }
    }


    /**
     * Creates a new Order for the Customer. If the order was successfully saved to the database then checkoutSuccessful
     * is called. Otherwise the Customer shopping cart is replaced with the Order shopping cart and call checkoutFailed
     */
    private void createOrder()
    {
        try
        {
            Order order = new Order(customer);
            order.setCurrentDate();
            if(order.saveOrderToDb())
            {
                checkoutSuccessful();
            }
            else
            {
                ProductStockCollection updatedCart = order.getCustomerCart();
                customer.setShoppingCart(updatedCart);
                checkoutFailed(order.getMessage());
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * When checkout was successful, all views call checkoutSuccessful
     */
    private void checkoutSuccessful()
    {
        for (StoreView v:views)
        {
            v.checkoutSuccessful();
        }
    }

    /**
     * When checkout has failed. All views call checkoutFailed with the specified message and then the inventory is
     * updated from the database
     *
     * @param msg               String, the message associated with why the checkout failed
     */
    private void checkoutFailed(String msg)
    {
        for (StoreView v:views)
        {
            v.checkoutFailed(msg);
        }

        setInventory();
    }


}

