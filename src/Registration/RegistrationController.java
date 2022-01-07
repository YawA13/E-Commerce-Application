package Registration;

import Store.Customer;
import Store.StoreController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationController implements ActionListener {

    private Registration model;
    private RegistrationFrame view;

    public RegistrationController(Registration model, RegistrationFrame view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String username = view.getUsername();
        String password = view.getPassword();

        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setUsername(username);
        model.setPassword(password);
        model.createNewAccount();
    }

    public void registrationSuccessful()
    {
        view.dispose();
        int customerId = model.getCustomerId();
        Customer customer = new Customer(customerId);
        new StoreController(customer);
    }
}
