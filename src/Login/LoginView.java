package Login;

/**
 * View of Login
 */
public interface LoginView {

    /**
     * Method For views when the customer enters the correct credentials and is able to log in
     */
    void loginSuccessful();

    /**
     * Method for views when the customer enters incorrect username password pair.
     */
    void loginFailed();
}
