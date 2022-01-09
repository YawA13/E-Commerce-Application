package Store;

import java.sql.Date;

public class Order {

    private int customerId;
    private Date date;
    private ProductStockCollection cart;

    public Order(int id, ProductStockCollection cart)
    {
        customerId = id;
        date = null;
        this.cart = cart;
    }

    public void setCurrentDate() {
        java.util.Date utilDate=new java.util.Date();
        date=new java.sql.Date(utilDate.getTime());
    }
}
