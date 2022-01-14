package Store;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

/**
 * Customer of the store
 */
public class Customer {

    /**
     * Customer Id
     */
    private final int id;

    /**
     * Customer shopping cart of products and quantity associated with it
     */
    private ProductStockCollection shoppingCart;

    /**
     * Constructor for Custmer
     *
     * @param id        int, the Customer id
     */
    public Customer(int id)
    {
        this.id = id;
        shoppingCart = new ProductStockMap();
    }

    /**
     * Get the Customer id
     *
     * @return          int, the Customer id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the shopping cart
     *
     * @return          ProductStockCollection, the Customer shopping cart
     */
    public ProductStockCollection getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Sets the shopping cart with another shopping cart
     *
     * @param shoppingCart      ProductStockCollection, a different shopping cart to replace the current
     */
    public void setShoppingCart(ProductStockCollection shoppingCart)
    {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Removes the entire stock of a specified product
     *
     * @param product           Product, the specific product
     */
    public void removeAll(Product product)
    {
        int stock = getProductStock(product);
        removeFromCart(product, stock);
    }


    //Delegate Methods for shopping cart
    /**
     * Check if a specific product is the shopping cart
     *
     * @param product       Product, the product to be checked
     * @return              boolean, true if the shopping cart contains the product; false otherwise
     */
    public boolean contains(Product product) {
        return shoppingCart.contains(product);
    }

    /**
     * Gets the amount of stock related to a specific product
     *
     * @param product           Product, the product specified
     * @return                  int, the stock of the product in the shopping cart
     */
    public int getProductStock(Product product) {
        return shoppingCart.getProductStock(product);
    }

    /**
     * Adds a specific stock to a product to the shopping cart. If the product is not in the shopping cart, it adds the
     * product with specified stock to the shopping cart
     *
     * @param product           Product, the product specified
     * @param stock             int, the stock to be added
     */
    public void addToCart(Product product, int stock) {
        shoppingCart.add(product, stock);
    }

    /**
     * Removes a specific stock from a product in the shopping cart. Removes the entire product when the stock becomes 0
     *
     * @param product           Product, the product specified
     * @param stock             int, the stock to be removed
     * @return                  boolean, true if the shopping cart contains the product and rmeoval occured; false otherwise
     */
    public boolean removeFromCart(Product product, int stock) {
        return shoppingCart.remove(product, stock);
    }

    /**
     * Gets the total cost to purchase all quantities of a specific product based on stock and base price of the product
     *
     * @param product           Product, the product specified
     * @return                  double, the cost to purchase
     */
    public double getTotalProductCost(Product product) {
        return shoppingCart.getTotalCost(product);
    }

    /**
     * Gets the total cost to purchase all the products in the shopping cart, with two decimal places
     *
     * @return              double, the total cost
     */
    public double getTotalCost() {
        BigDecimal bd = new BigDecimal(shoppingCart.getTotalCost()).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    /**
     * Gets a collection of all the products in the entire shopping cart
     *
     * @return          Collection<Product>, collection of products
     */
    public Collection<Product> getAllProducts() {
        return shoppingCart.getAllProducts();
    }











}
