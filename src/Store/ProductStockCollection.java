package Store;

import java.util.Collection;

/**
 * Interface to store a collection of products paired with their stock
 */
public interface ProductStockCollection {

    /**
     * Check if a specific product is the collection
     *
     * @param product       Product, the product to be checked
     * @return              boolean, true if the collection contains the product; false otherwise
     */
    boolean contains(Product product);

    /**
     * Gets the amount of stock related to a specific product
     *
     * @param product           Product, the product specified
     * @return                  int, the stock of the product in the collection
     */
    int getProductStock(Product product);

    /**
     * Adds a specific stock to a product to the collection. If the product is not in the collection, it adds the
     * product with specified stock to the collection
     *
     * @param product           Product, the product specified
     * @param stock             int, the stock to be added
     */
    void add(Product product, int stock);


    /**
     * Removes a specific stock from a product in the collection. Removes the entire product when the stock becomes 0
     *
     * @param product           Product, the product specified
     * @param stock             int, the stock to be removed
     * @return                  boolean, true if the collection contains the product and rmeoval occured; false otherwise
     */
    boolean remove(Product product, int stock);


    /**
     * Gets the total cost to purchase all quantities of a specific product based on stock and base price of the product
     *
     * @param product           Product, the product specified
     * @return                  double, the cost to purchase
     */
    double getTotalCost(Product product);

    /**
     * Gets the total cost to purchase all the products in the collection
     *
     * @return              double, the total cost
     */
    double getTotalCost();

    /**
     * Gets a collection of all the products in the entire ProductStockCollection
     *
     * @return          Collection<Product>, collection of products
     */
    Collection<Product> getAllProducts();

}
