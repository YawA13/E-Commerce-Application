package Registration;

import Store.Customer;
import Store.StoreController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller of Registration
 */
public class RegistrationController implements ActionListener {

    /**
     * The model associated with the controller
     */
    private Registration model;

    /**
     * The view associated with the view
     */
    private RegistrationFrame view;

    /**
     * Constructor for RegistrationController
     *
     * @param model     Registration, the model
     * @param view      RegistrationFrame, the view
     */
    public RegistrationController(Registration model, RegistrationFrame view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Determine what action in the view occurs and call the appropriate method
     *
     * @param e         ActionEvent, the action event
     */
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

    /**
     * Close the view and creates a customer model based of the id from model. Create StoreController object with the
     * newly created customer object
     */
    public void registrationSuccessful()
    {
        view.dispose();
        int customerId = model.getCustomerId();
        Customer customer = new Customer(customerId);
        new StoreController(customer);
    }
}
