package Login;

import Store.Customer;
import Store.StoreController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private Login model;
    private LoginFrame view;

    public LoginController(Login model, LoginFrame view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String username = view.getUsername();
        String password = view.getPassword();

        model.setUsername(username);
        model.setPassword(password);
        model.requestLogin();
    }

    public void loginSuccessful()
    {
        view.dispose();
        int customerId = model.getCustomerId();
        Customer customer = new Customer(customerId);
        new StoreController(customer);
    }
}
