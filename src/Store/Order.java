package Store;

import General.DatabaseConnection;

import java.sql.*;

public class Order {

    private Customer customer;
    private Date date;
    private int orderId;
    private String message;

    public Order(Customer customer)
    {
        this.customer = customer;
        date = null;
        orderId = 0;
        message = null;
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
                connection.commit();
                connection.setAutoCommit(true);
                DatabaseConnection.closeConnection();
                return true;
            }
            message = "Not all the items in your cart are available";


        }
        catch (SQLException e)
        {
            e.printStackTrace();
            connection.rollback(savepoint1);
            connection.setAutoCommit(true);
            DatabaseConnection.closeConnection();
            message = "Problem with the Database. Please try again";
            return false;
        }

        connection.rollback(savepoint1);
        connection.setAutoCommit(true);
        DatabaseConnection.closeConnection();
        return false;

    }


    private boolean checkProductTable(Connection connection)
    {
        try
        {
            for (Product product: customer.getAllProducts())
            {
                int productId = product.getId();
                int stock = customer.getProductStock(product);
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
            for (Product product: customer.getAllProducts())
            {
                int productId = product.getId();
                int stock = customer.getProductStock(product);

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
            statement.setInt(1,customer.getId());
            statement.setDate(2,date);
            statement.execute();


            statement = connection.prepareStatement("select * from orders where customerId = ? and date = ?");
            statement.setInt(1,customer.getId());
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
            for (Product product: customer.getAllProducts())
            {
                int productId = product.getId();
                int quantity = customer.getProductStock(product);

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

    public String getMessage()
    {
        return message;
    }

}
