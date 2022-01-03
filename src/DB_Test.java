

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Properties prop = new Properties();
        String fileName = "db.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        String dbUrl = prop.getProperty("db.url");
        String dbUsername = prop.getProperty("db.username");
        String dbPassword = prop.getProperty("db.password");





        try
        {
            Connection myConn = DriverManager.getConnection(dbUrl,dbUsername, dbPassword);

            Statement s = myConn.createStatement();
            PreparedStatement st = myConn.prepareStatement("select * from customers where firstname = ?");
            st.setString(1, "Yaw");
            ResultSet res = st.executeQuery();
            //ResultSet res = s.executeQuery("select * from customers where firstname = 'Yaw' ");
            if(res.next())
            {
                System.out.println(res.getString("firstName")+" , "+res.getString("username")+" , "+res.getInt("id"));
            }

            while(res.next())
            {

            }

            myConn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
