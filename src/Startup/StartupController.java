package Startup;

import Registration.RegistrationFrame;
import Login.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for Startup
 */
public class StartupController implements ActionListener {

    /**
     * The model associated with the controller
     */
    private Startup model;

    /**
     * The view associated with the controller
     */
    private StartupFrame view;

    /**
     * Constructor for StartupController
     *
     * @param model     Startup, the model
     * @param view      StartupFrame, the view
     */
    public StartupController(Startup model, StartupFrame view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Determine what action occurs and call the appraise method
     *
     * @param e     ActionEvent, the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand())
        {
            case "register":
                model.register();
                break;
            case "login":
                model.login();
                break;
            default:
                break;
        }

    }

    /**
     * Close the frame and open registration frame
     */
    public void register()
    {
        view.dispose();
        new RegistrationFrame();
    }

    /**
     * Close the frame and open login frame
     */
    public void login()
    {
        view.dispose();
        new LoginFrame();
    }
}
