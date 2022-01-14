package Store;

import General.DatabaseConnection;
import java.sql.*;
import java.util.Iterator;

/**
 * Create and send Order to Database
 */
public class Order {

    /**
     * Customer placing the Order
     */
    private Customer customer;

    /**
     * Date of the Order
     */
    private Date date;

    /**
     * Order Id
     */
    private int orderId;

    /**
     * message generated from failed Order
     */
    private String message;

    /**
     * Constructor for Order
     *
     * @param customer          Customer, the customer placing the Order
     */
    public Order(Customer customer)
    {
        this.customer = customer;
        date = null;
        orderId = 0;
        message = null;
    }

    /**
     * Sets the current date of the Order;
     */
    public void setCurrentDate() {
        java.util.Date utilDate=new java.util.Date();
        date=new java.sql.Date(utilDate.getTime());
    }

    /**
     * Attempts to save the order to relevant tables within the database
     *
     * @return                  boolean, true if the order was saved to the database;false otherwise
     * @throws SQLException
     */
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


    /**
     * Checks if there is enough stock in the product database to purchase all the items in the customer shopping cart.
     * For any product that can't be purchased the entire product is removed from the cart and is added to the message
     *
     * @param connection        Connection, connection to the database
     * @return                  boolean, true if all products in the customer shopping cart can be bought
     */
    private boolean checkProductTable(Connection connection)
    {
        boolean isCheckSuccessful = true;

        try
        {
            Iterator<Product> iterator = customer.getAllProducts().iterator();
            while (iterator.hasNext())
            {
                Product product = iterator.next();
                int productId = product.getId();
                int stock = customer.getProductStock(product);
                PreparedStatement statement = connection.prepareStatement("select * from products where productId=? and stock >= ?");
                statement.setInt(1,productId);
                statement.setInt(2,stock);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next())
                {
                    isCheckSuccessful =  false;
                    setFailedCheckMessage(product.getName());
                    iterator.remove();
                }


            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
            isCheckSuccessful = false;
        }

        return isCheckSuccessful;
    }

    /**
     * Subtracts the stock for each product in the products table with the stock in the customer shopping cart
     *
     * @param connection                Connection, connection to the database
     */
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


    /**
     * Adds a new row to the order table with the customer id and the current date. Sets the orderId with the generated
     * orderId in the database.
     *
     * @param connection            Connection, connection to the database
     */
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

    /**
     * For each product in customer shopping cart adds a new row to the orderDetail table with the
     * order id, product id and quantity.
     *
     * @param connection            Connection, connection to the database
     */
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

    /**
     * Gets the order message
     *
     * @return              String, the order message
     */
    public String getMessage()
    {
        return message;
    }


    /**
     * Gets the updated customer cart
     *
     * @return              ProductStockCollection, the Customer new shopping cart
     */
    public ProductStockCollection getCustomerCart()
    {
        return customer.getShoppingCart();
    }

    /**
     * Sets the order message with the name of the product that failed the check
     *
     * @param productName
     */
    private void setFailedCheckMessage(String productName)
    {
        if(message==null)
        {
            message = "Not all the items in your cart are available. As a result the following items have been removed from your cart:";
        }

        message += "\n"+productName;

    }

}
