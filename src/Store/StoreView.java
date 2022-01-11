package Store;

import java.util.Collection;

public interface StoreView {

    void addProductsToGUI(ProductStockCollection inventory, ProductStockCollection cart);

    void updateCustomerCart(Product product, boolean addEnable, boolean removeEnable);

    void displayCustomerCart(String cart);

    void checkoutSuccessful();

    void checkoutFailed(String message);
}
