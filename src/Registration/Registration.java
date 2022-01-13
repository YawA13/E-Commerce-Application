package Registration;

import General.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model of Registration
 */
public class Registration {

    /**
     * Customer first name
     */
    private String firstName;

    /**
     * Customer last name
     */
    private String lastName;

    /**
     * Customer username
     */
    private String username;

    /**
     * Customer password
     */
    private String password;

    /**
     * Customer Id
     */
    private int customerId;

    /**
     * List of views associated with Registration model
     */
    private List<RegistrationView> views;

    /**
     * Database connection
     */
    private Connection connection;

    /**
     * Constructor for Registration
     *
     * @param firstName     String, Customer first name
     * @param lastName      String, Customer last name
     * @param username      String, Customer username
     * @param password      String, Customer passowrd
     */
    public Registration(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        customerId = 0;
        connection = null;
        views = new ArrayList<>();
    }

    /**
     * Default constructor
     */
    public Registration() {
        this(null, null, null, null);
    }

    /**
     * Set first name of Customer
     *
     * @param firstName     String, Customer first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set last name of Customer
     *
     * @param lastName      String, Customer last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set username of Customer
     *
     * @param username      String, Customer username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set password of Customer
     *
     * @param password      String, Customer password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Adds a view to list of views
     *
     * @param newView       RegistrationView, the new view to be added
     */
    public void addView(RegistrationView newView) {
        views.add(newView);
    }

    /**
     * Removes an existing view from list of views
     *
     * @param oldView       RegistationView, the view to be removed
     */
    public void removeView(RegistrationView oldView) {
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).equals(oldView)) {
                views.remove(i);
                break;
            }
        }
    }

    /**
     * Attempts to create a new customer. Checks if there is an existing username and attempts to insert the customer
     * in the database
     */
    public void createNewAccount() {
        connection = DatabaseConnection.getConnection();
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from customers where username = ?");
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            //if username already exists
            if (resultSet.next()) {
                registrationFailed();
            } else {
                registrationSuccessful();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Attempts to insert a new customer account to database
     */
    private void registrationSuccessful() {
        if(isAnyInputEmpty())
        {
            registrationFailed();
        }
        else
        {
            try {
                String query = "insert into customers (firstName, lastName, username, password) values (?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, username);
                statement.setString(4, password);
                statement.execute();

                statement = connection.prepareStatement("select * from customers where username = ?");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                {
                    customerId = resultSet.getInt("customerId");
                }

                DatabaseConnection.closeConnection();

                for (RegistrationView v : views) {
                    v.registrationSuccessful();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if any of first name, last name, username and password is empty
     *
     * @return      boolean, true if any input is empty;false if otherwise
     */
    private boolean isAnyInputEmpty()
    {
        return firstName.trim().isEmpty() || lastName.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty();
    }

    /**
     * Close database connect and calls registrationFailed method for all views in the list
     */
    private void registrationFailed() {
        DatabaseConnection.closeConnection();
        for (RegistrationView v : views) {
            v.registrationFailed();
        }
    }

    /**
     * Gets the Customer id
     *
     * @return      int, the id of the Customer
     */
    public int getCustomerId() {
        return customerId;

    }

}
