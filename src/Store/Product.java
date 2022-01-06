package Store;

public class Product {
    private final String name;
    private double price;
    private String img; //potential change


    public Product(String name, double price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }


}
