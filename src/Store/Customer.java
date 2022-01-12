package Store;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class Customer {

    private final int id;
    private ProductStockCollection shoppingCart;

    public Customer(int id)
    {
        this.id = id;
        shoppingCart = new ProductStockMap();
    }

    public int getId() {
        return id;
    }

    public ProductStockCollection getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ProductStockCollection shoppingCart)
    {
        this.shoppingCart = shoppingCart;
    }

    public void removeAll(Product product)
    {
        int stock = getProductStock(product);
        removeFromCart(product, stock);
    }


    //Delegate Methods for shopping cart
    public boolean contains(Product product) {
        return shoppingCart.contains(product);
    }

    public int getProductStock(Product product) {
        return shoppingCart.getProductStock(product);
    }

    public void addToCart(Product product, int stock) {
        shoppingCart.add(product, stock);
    }

    public boolean removeFromCart(Product product, int stock) {
        return shoppingCart.remove(product, stock);
    }

    public double getTotalProductCost(Product product) {
        return shoppingCart.getTotalCost(product);
    }

    public double getTotalCost() {
        BigDecimal bd = new BigDecimal(shoppingCart.getTotalCost()).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    public Collection<Product> getAllProducts() {
        return shoppingCart.getAllProducts();
    }











}
