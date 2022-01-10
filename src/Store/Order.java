package Store;

import General.DatabaseConnection;

import java.sql.*;

public class Order {

    private int customerId;
    private Date date;
    private ProductStockCollection cart;
    private ProductStockCollection inventory;

    public Order(int id, ProductStockCollection cart)
    {
        customerId = id;
        date = null;
        this.cart = cart;
        inventory = new ProductStockMap();
    }

    public void setCurrentDate() {
        java.util.Date utilDate=new java.util.Date();
        date=new java.sql.Date(utilDate.getTime());
    }

    public boolean saveOrderToDb() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Savepoint savepoint1 = null;

        try
        {
            connection.setAutoCommit(false);
            savepoint1 = connection.setSavepoint("Savepoint1");

            return true;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            connection.rollback(savepoint1);
            DatabaseConnection.closeConnection();
            return false;
        }

    }
}
