import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Login {

    private String username;
    private String password;
    private int customerId;
    private List<LoginView> views;

    public Login() {
        username = null;
        password = null;
        customerId = 0;
        views = new ArrayList<>();
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void addView(LoginView newView)
    {
        views.add(newView);
    }

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
    public void checkForAccount()
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
            if (resultSet.next())
            {
                customerId = resultSet.getInt("id");
                for (LoginView v:views)
                {
                    v.loginSuccessful();
                }
            }
            else
            {
                for (LoginView v:views)
                {
                    v.loginFailed();
                }
            }

            DatabaseConnection.closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    public int getCustomerId()
    {
        return customerId;
    }

}
