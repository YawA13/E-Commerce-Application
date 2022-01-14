package Store;

/**
 * View for Store
 */
public interface StoreView {

    /**
     * Setups the store GUI with the inventory products
     *
     * @param inventory             ProductStockCollection, collection of products in Store inventory
     * @param cart                  ProductStockCollection, collection of products in Customer shopping cart
     */
    void addProductsToGUI(ProductStockCollection inventory, ProductStockCollection cart);

    /**
     * Update the store after a product is added or removed to customer shopping cart
     *
     * @param product               Product, the product added or removed to shopping cart
     * @param addEnable             boolean, true if add button should be enabled; false otherwise
     * @param removeEnable          boolean, true if remove button should be enabled; false otherwise
     */
    void updateCustomerCart(Product product, boolean addEnable, boolean removeEnable);

    /**
     * Display the customer cart
     *
     * @param cart                  String, the Customer's cart in text-form
     */
    void displayCustomerCart(String cart);

    /**
     * Method when checkout has been successful
     */
    void checkoutSuccessful();

    /**
     * Method when checkout has failed
     *
     * @param message               String, the error message of why the checkout failed
     */
    void checkoutFailed(String message);
}
