package Login;

import General.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model of Login
 */
public class Login {

    /**
     * Customer username
     */
    private String username;

    /**
     * Customer passowrd
     */
    private String password;

    /**
     * Customer Id
     */
    private int customerId;

    /**
     * List of views associated with Login
     */
    private List<LoginView> views;

    /**
     * Default constructor
     */
    public Login() {
        username = null;
        password = null;
        customerId = 0;
        views = new ArrayList<>();
    }

    /**
     * Set username of Customer
     *
     * @param username          String, Customer username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Set password of Customer
     *
     * @param password          String, Customer password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Add new view to existing list of views
     *
     * @param newView           LoginView, the new view to be added
     */
    public void addView(LoginView newView)
    {
        views.add(newView);
    }

    /**
     * Removes an existing view from list of views
     *
     * @param oldView           LoginView, the view to be removed
     */
    public void removeView(LoginView oldView)
    {
        for (int i = 0; i < views.size(); i++)
        {
            if(views.get(i).equals(oldView))
            {
                views.remove(i);
                break;
            }
        }
    }

    /**
     * Checks if there is a customer account paired with the username and password
     */
    public void requestLogin()
    {
        Connection connection = DatabaseConnection.getConnection();
        ResultSet resultSet;
        try
        {
            PreparedStatement statement = connection.prepareStatement("select * from customers where username = ? and password = ?");
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();

            //if username and password are correct
            if (resultSet.next()) {
                loginSuccessful(resultSet);
            }
            else {
                loginFailed();
            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    /**
     * Sets the Customer Id to the Customer Id from the database. calls loginSuccessful method for all views in the list
     *
     * @param resultSet         ResultSet, the data associated from the matching username password pair
     */
    private void loginSuccessful(ResultSet resultSet)
    {
        try
        {
            customerId = resultSet.getInt("customerId");
            DatabaseConnection.closeConnection();

            for (LoginView v:views)
            {
                v.loginSuccessful();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * calls loginFailed method for all views in the list
     */
    private void loginFailed()
    {
        DatabaseConnection.closeConnection();
        for (LoginView v:views)
        {
            v.loginFailed();
        }
    }

    /**
     * Gets the Customer id
     *
     * @return              int, the Customer id
     */
    public int getCustomerId()
    {
        return customerId;
    }

}
