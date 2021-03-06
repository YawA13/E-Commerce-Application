package Startup;


import javax.swing.*;
import java.awt.*;

/**
 * Concrete view of Startup
 */
public class StartupFrame extends JFrame implements  StartupView{

    /**
     * Controller of Startup
     */
    private StartupController controller;

    /**
     * Default constructor
     */
    public StartupFrame()
    {
        super("Welcome");

        //create model and controller;
        Startup model = new Startup();
        controller = new StartupController(model,this);
        model.addView(this);

        //Outer panel directly connected to the frame to display colour
        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setBackground(new Color(0,103,103));

        //panel to add the main buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        //set register button
        JButton registerBtn = new JButton("Register");
        registerBtn.setActionCommand("register");
        registerBtn.addActionListener(controller);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //set the login button
        JButton loginBtn = new JButton("Login");
        loginBtn.setActionCommand("login");
        loginBtn.addActionListener(controller);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //adds all components to main panel
        mainPanel.add(registerBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(loginBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));


        colorPanel.add(mainPanel);
        this.add(colorPanel);

        //set common properties for the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);

    }

    /**
     * Open dialog box with registration message
     */
    @Override
    public void register()
    {
        JOptionPane.showMessageDialog(this,
                "We are redirecting you to register",
                "Register",
                JOptionPane.INFORMATION_MESSAGE);

        controller.register();
    }

    /**
     * Open dialog with login message
     */
    @Override
    public void login()
    {
        JOptionPane.showMessageDialog(this,
                "We are redirecting you to login",
                "Login",
                JOptionPane.INFORMATION_MESSAGE);
        controller.login();
    }

    /**
     * Main method to start the application
     * @param args          String[], list of arguments
     */
    public static void main(String[] args) {
        new StartupFrame();
    }
}
