package Store;

import General.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Store {

    private Customer customer;
    private List<StoreView> views;
    private ProductStockCollection inventory;

    public Store()
    {
        customer = null;
        views = new ArrayList<>();
        inventory = new ProductStockMap();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addView(StoreView newView) {
        views.add(newView);
    }

    public void removeView(StoreView oldView) {
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).equals(oldView)) {
                views.remove(i);
                break;
            }
        }
    }

    public void setInventory(ProductStockCollection inventory) {
        this.inventory = inventory;
    }

    public void setInventory() {

        Connection connection = DatabaseConnection.getConnection();

        try
        {
            PreparedStatement statement = connection.prepareStatement("select * from products");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {

                int productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                double productPrice = resultSet.getDouble("price");
                String productImg = resultSet.getString("img");
                int productStock = resultSet.getInt("stock");

                Product product = new Product(productId,productName,productPrice,productImg);
                inventory.add(product, productStock);
            }
            setGUIProducts();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
    }

    private void setGUIProducts()
    {
        for (StoreView v:views)
        {
            v.addProductsToGUI(inventory.getAllProducts());
        }
    }

    public void customerAddProduct(Product product)
    {
        customer.addToCart(product, 1);
        inventory.remove(product, 1);

        int stockLeft = inventory.getProductStock(product);
        boolean addEnabel = (stockLeft==0)?false:true;
        boolean removeEnabel = true;

        updateViewCart(product, addEnabel,removeEnabel);

    }

    public void customerRemoveProduct(Product product)
    {
        customer.removeFromCart(product,1);
        inventory.add(product, 1);

        boolean addEnabel = true;
        boolean removeEnabel = customer.contains(product);

        updateViewCart(product, addEnabel,removeEnabel);
    }

    private void updateViewCart(Product product, boolean addEnable, boolean removeEnable)
    {
        for (StoreView v:views)
        {
           v.updateCustomerCart(product, addEnable, removeEnable);
        }
    }

    private String turnCartToText()
    {
        StringBuilder cart = new StringBuilder();
        Collection<Product> cartProducts = customer.getAllProducts();

        if(cartProducts.size()>0)
        {
            String format = "%-30s %5s %10s\n";
            String formatForProducts = "%.30s %5d %10.2f\n";

            cart.append(String.format(format, "Item", "Qty", "Price"));
            cart.append(String.format(format, "----", "---", "-----"));

            for(Product product: customer.getAllProducts())
            {
                String itemName = product.getName();
                int qty = customer.getProductStock(product);
                double price = product.getPrice();
                cart.append(String.format(formatForProducts, itemName, qty, price));
            }
            double total = customer.getTotalCost();

            cart.append(String.format(format, "----", "---", "-----"));
            cart.append("Total: ");
            cart.append(total);
        }
        else
        {
            cart.append("The Shopping Cart is Empty");
        }

        return cart.toString();
    }

    public void getCustomerCartInText()
    {
        String cartText = turnCartToText();

        for (StoreView v:views)
        {
            v.displayCustomerCart(cartText);
        }
    }


    public void customerCheckout()
    {
        if(customer.getAllProducts().size()>0)
        {
            try
            {
                Order order = new Order(customer.getId(), customer.getShoppingCart());
                order.setCurrentDate();
                if(order.saveOrderToDb())
                {
                    checkoutSuccessful();
                }
                else
                {
                    checkoutFailed(order.getMessage());
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            checkoutFailed("No Items in Cart");
        }
    }


    private void checkoutSuccessful()
    {
        for (StoreView v:views)
        {
            v.checkoutSuccessful();
        }
    }

    private void checkoutFailed(String msg)
    {
        for (StoreView v:views)
        {
            v.checkoutFailed(msg);
        }
    }


}

