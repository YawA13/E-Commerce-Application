package Store;

import General.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    private boolean checkDbInventory(Connection connection)
    {
        try
        {
            for (Product product: cart.getAllProducts())
            {
                int productId = product.getId();
                int stock = cart.getProductStock(product);
                PreparedStatement statement = connection.prepareStatement("select * from products where productId=? and stock >= ?");
                statement.setInt(1,productId);
                statement.setInt(2,stock);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    return false;
                }
            }

            return true;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private void updateProductsTable(Connection connection)
    {
        try
        {
            for (Product product: cart.getAllProducts())
            {
                int productId = product.getId();
                int stock = cart.getProductStock(product);

                PreparedStatement statement = connection.prepareStatement("Update products set stock = stock - ? where productId = ?");
                statement.setInt(1,stock);
                statement.setInt(2,productId);
                statement.execute();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
