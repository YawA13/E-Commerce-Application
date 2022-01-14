package Store;

import java.util.*;

/**
 * Concrete implementation of ProductStockCollection using linkedHashMap
 */
public class ProductStockMap implements ProductStockCollection {

    /**
     * Map of product keys and stock values
     */
    private Map<Product,Integer> list;

    /**
     * Default constructor
     */
    public ProductStockMap()
    {
        list = new LinkedHashMap<>();
    }

    /**
     * Check if a specific product is the collection
     *
     * @param product       Product, the product to be checked
     * @return              boolean, true if the collection contains the product; false otherwise
     */
    public boolean contains(Product product)
    {
        return list.containsKey(product);
    }

    /**
     * Gets the amount of stock related to a specific product
     *
     * @param product           Product, the product specified
     * @return                  int, the stock of the product in the collection
     */
    public int getProductStock(Product product)
    {
        if(contains(product))
        {
            return list.get(product);
        }
        return 0;
    }

    /**
     * Adds a specific stock to a product to the collection. If the product is not in the collection, it adds the
     * product with specified stock to the collection
     *
     * @param product           Product, the product specified
     * @param stock             int, the stock to be added
     */
    public void add(Product product, int stock)
    {
        if (contains(product))
        {
            stock += getProductStock(product);
        }
        list.put(product,stock);
    }

    /**
     * Removes a specific stock from a product in the collection. Removes the entire product when the stock becomes 0
     *
     * @param product           Product, the product specified
     * @param stock             int, the stock to be removed
     * @return                  boolean, true if the collection contains the product and rmeoval occured; false otherwise
     */
    public boolean remove(Product product, int stock)
    {
        if (contains(product))
        {
            int newStock = getProductStock(product) - stock;
            if(newStock>0)
            {
                list.put(product, newStock);
            }
            else
            {
                list.remove(product);
            }

            return true;
        }
        return false;
    }

    /**
     * Gets the total cost to purchase all quantities of a specific product based on stock and base price of the product
     *
     * @param product           Product, the product specified
     * @return                  double, the cost to purchase
     */
    public double getTotalCost(Product product)
    {
        double total = 0;
        if (contains(product))
        {
            int stock = getProductStock(product);
            total = product.getPrice() * stock;

        }
        return total;
    }

    /**
     * Gets the total cost to purchase all the products in the collection
     *
     * @return              double, the total cost
     */
    public double getTotalCost()
    {
        double total = 0;
        for (Product product: list.keySet())
        {
            total += getTotalCost(product);
        }

        return total;
    }

    /**
     * Gets a collection of all the products in the entire ProductStockCollection
     *
     * @return          Collection<Product>, collection of products
     */
    public Collection<Product> getAllProducts()
    {
        return list.keySet();
    }

}
