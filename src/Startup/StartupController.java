package Startup;

import Registration.Registration;
import Login.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupController implements ActionListener {

    private Startup model;
    private StartupFrame view;

    public StartupController(Startup model, StartupFrame view) {
        this.model = model;
        this.view = view;
    }

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

    public void register()
    {
        view.dispose();
        new Registration();
    }

    public void login()
    {
        view.dispose();
        new Login();
    }
}
