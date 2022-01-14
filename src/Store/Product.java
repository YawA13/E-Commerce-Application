package Store;

/**
 * Product
 */
public class Product {
    /**
     * Product id
     */
    private final int id;

    /**
     * Product name
     */
    private final String name;

    /**
     * Product price
     */
    private double price;

    /**
     * Product image url
     */
    private String imgUrl; //potential change

    /**
     * Constructor for Product
     *
     * @param id            int, Product id
     * @param name          String, Product name
     * @param price         double, Product price
     * @param img           String, Product image url
     */
    public Product(int id, String name, double price, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = "/images/"+img;
    }

    /**
     * Get Product id
     *
     * @return          int, Product id
     */
    public int getId() {
        return id;
    }

    /**
     * Get Product name
     *
     * @return          String, Product name
     */
    public String getName() {
        return name;
    }

    /**
     * Get Product price
     *
     * @return          double, Product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get Product image url
     *
     * @return          String, Product image url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Checks if the Product is equal to another Object based off the Product id
     *
     * @param obj           Object, the object being compared to the current Product (in most cases another Product object)
     * @return              true if two objects have the same Product id; false otherwise
     */
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

    /**
     * Gets the hashcode of the Product, which is just the Product id
     *
     * @return          int, the Product id
     */
    @Override
    public int hashCode() {
        return this.id;
    }
}
