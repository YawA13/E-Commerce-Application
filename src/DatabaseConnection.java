import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection = null;

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

    public static Connection getConnection()
    {
        if (connection == null)
            connection = createConnection();
        return connection;
    }

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



