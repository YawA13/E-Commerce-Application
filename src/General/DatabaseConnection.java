package General;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton Class to retrive the database connection based off an external config file
 */
public class DatabaseConnection {

    /**
     * Connection to database
     */
    private static Connection connection = null;

    /**
     * sets connection to MYSQL database using properites in config file and then return the connection
     *
     * @return          Connection, a specific connection to the MYSQL database
     */
    private static Connection createConnection ()
    {
        Properties prop = new Properties();
        String fileName = "db.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);

            String dbUrl = prop.getProperty("db.url");
            String dbUsername = prop.getProperty("db.username");
            String dbPassword = prop.getProperty("db.password");

            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * When connection is null, connection is set by createConnection. Gets the connection
     *
     * @return          Connection, the connection to MYSQL database
     */
    public static Connection getConnection()
    {
        if (connection == null)
            connection = createConnection();
        return connection;
    }

    /**
     * Closes the connection and sets it to null to help identify the connection is closed.
     */
    public static void closeConnection()
    {
        try
        {
            connection.close();
            connection = null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



