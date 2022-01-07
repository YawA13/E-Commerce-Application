package Store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ProductStockMap implements ProductStockCollection {

    private HashMap<Product,Integer> list;

    public ProductStockMap()
    {
        list = new HashMap<>();
    }

    public boolean contains(Product product)
    {
        return list.containsKey(product);
    }

    public int getProductStock(Product product)
    {
        if(contains(product))
        {
            return list.get(product);
        }
        return 0;
    }

    public void add(Product product, int stock)
    {
        if (contains(product))
        {
            stock += getProductStock(product);
        }
        list.put(product,stock);
    }

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

    public double getTotalCost()
    {
        int total = 0;
        for (Product product: list.keySet())
        {
            total += getTotalCost(product);
        }

        return total;
    }

    public Collection<Product> getAllProducts()
    {
        return list.keySet();
    }

}
