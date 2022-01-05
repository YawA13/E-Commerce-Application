import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {

    private Register model;
    private RegisterView view;

    public RegisterController(Register model, RegisterView view) {
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

    public void loginSuccessful()
    {
        view.dispose();
        int customerId = model.getCustomerId();
        new StoreController(customerId);
    }
}
