public class StoreProduct extends Product{

    private final int id;
    private int stock;

    public StoreProduct(String name, double price, String img, int id, int stock)
    {
        super(name, price, img);
        this.id = id;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public void addStock(int amountToAdd)
    {
        this.stock += amountToAdd;
    }

    public void removeStock(int amountToRemove)
    {
        this.stock -= amountToRemove;
    }

    public double getTotalAmount()
    {
        return getPrice()*stock;
    }
}
