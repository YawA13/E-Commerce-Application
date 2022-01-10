package Store;

import General.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int customerId;
    private Date date;
    private ProductStockCollection cart;
    private int orderId;

    public Order(int id, ProductStockCollection cart)
    {
        customerId = id;
        date = null;
        this.cart = cart;
        orderId = 0;
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

            if(checkProductTable(connection))
            {
                updateProductsTable(connection);
                updateOrdersTable(connection);
                updateOrderDetailsTable(connection);
                return true;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            connection.rollback(savepoint1);
            DatabaseConnection.closeConnection();
            return false;
        }

        connection.rollback(savepoint1);
        DatabaseConnection.closeConnection();
        return false;

    }


    private boolean checkProductTable(Connection connection)
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


    private void updateOrdersTable(Connection connection)
    {
        try
        {
            String query = "insert into orders (customerId, date) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,customerId);
            statement.setDate(2,date);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                orderId = resultSet.getInt("orderId");
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void updateOrderDetailsTable(Connection connection)
    {
        try
        {
            for (Product product: cart.getAllProducts())
            {
                int productId = product.getId();
                int quantity = cart.getProductStock(product);

                String query = "insert into orderdetails (orderId, productId, quantity) values (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setInt(1,orderId);
                statement.setInt(2,productId);
                statement.setInt(3,quantity);
                statement.execute();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
