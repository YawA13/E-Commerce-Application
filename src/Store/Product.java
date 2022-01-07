package Store;

public class Product {
    private final int id;
    private final String name;
    private double price;
    private String img; //potential change


    public Product(int id, String name, double price, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product))
        {
            return false;
        }
        else if (obj == this)
        {
            return true;
        }
        else
        {
           return  ((Product) obj).id == this.id;
        }
    }


}
