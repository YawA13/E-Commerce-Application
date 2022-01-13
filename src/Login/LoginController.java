package Login;

import Store.Customer;
import Store.StoreController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller of Login
 */
public class LoginController implements ActionListener {

    /**
     * Login Model
     */
    private Login model;

    /**
     * Login View
     */
    private LoginFrame view;

    /**
     * Login Constructor
     *
     * @param model         Login, the model
     * @param view          LoginFrame, the view
     */
    public LoginController(Login model, LoginFrame view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Sets the username, password of model from the respective text fields in the view. Calls the requestLogin method
     * on the model.
     *
     * @param e              ActionEvent, the action event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String username = view.getUsername();
        String password = view.getPassword();

        model.setUsername(username);
        model.setPassword(password);
        model.requestLogin();
    }

    /**
     * Close the view and creates a customer model based of the id from model. Create StoreController object with the
     * newly created customer object
     */
    public void loginSuccessful()
    {
        view.dispose();
        int customerId = model.getCustomerId();
        Customer customer = new Customer(customerId);
        new StoreController(customer);
    }
}
