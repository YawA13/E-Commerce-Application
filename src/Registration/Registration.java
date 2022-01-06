package Registration;

import General.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Registration {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int customerId;
    private List<RegistrationView> views;
    private Connection connection;


    public Registration(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        customerId = 0;
        connection = null;
        views = new ArrayList<>();
    }

    public Registration() {
        this(null, null, null, null);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addView(RegistrationView newView) {
        views.add(newView);
    }

    public void removeView(RegistrationView oldView) {
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).equals(oldView)) {
                views.remove(i);
                break;
            }
        }
    }

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

            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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
                    customerId = resultSet.getInt("id");
                }


                for (RegistrationView v : views) {
                    v.registrationSuccessful();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isAnyInputEmpty()
    {
        return firstName.trim().isEmpty() || lastName.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty();
    }

    private void registrationFailed() {
        for (RegistrationView v : views) {
            v.registrationFailed();
        }
    }

    public int getCustomerId() {
        return customerId;

    }

}
