package Store;

import java.util.Collection;
import java.util.HashMap;

public interface ProductStockCollection {

    boolean contains(Product product);


    int getProductStock(Product product);


    void add(Product product, int stock);


    boolean remove(Product product, int stock);


    double getTotalCost(Product product);


    double getTotalCost();

    Collection<Product> getAllProducts();

}
