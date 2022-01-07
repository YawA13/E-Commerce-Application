package Store;


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
        return shoppingCart.getTotalCost();
    }

    public Collection<Product> getAllProducts() {
        return shoppingCart.getAllProducts();
    }










}
