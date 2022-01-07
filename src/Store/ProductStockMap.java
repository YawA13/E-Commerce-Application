package Store;

import java.util.HashMap;

public class ProductStockMap implements ProductStockCollection{

    private HashMap<Product,Integer> list;

    public ProductStockMap()
    {
        list = new HashMap<Product, Integer>();
    }

    public boolean contains(Product product)
    {
        return list.containsKey(product);
    }

    public int getProductStock(Product product)
    {
        return list.get(product);
    }

    public void add(Product product, int stock)
    {
        if (contains(product))
        {
            stock += getProductStock(product);
        }
        list.put(product,stock);
    }

    public void remove(Product product, int stock)
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
        }
    }

    public double getTotalCost(Product product)
    {
        int stock = getProductStock(product);
        double total = product.getPrice() * stock;
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

}
