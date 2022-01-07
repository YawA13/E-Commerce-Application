package Store;

public class Product {
    private final int id;
    private final String name;
    private double price;
    private String imgUrl; //potential change


    public Product(int id, String name, double price, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = img;
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

    public String getImgUrl() {
        return imgUrl;
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
