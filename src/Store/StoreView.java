package Store;

import java.util.Collection;

public interface StoreView {

    void addProductsToGUI(Collection<Product> products);

    void updateCustomerCart(Product product, boolean addEnable, boolean removeEnable);

    void displayCustomerCart(String cart);

    void checkoutSuccessful();

    void checkoutFailed();
}
