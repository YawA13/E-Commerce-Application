package Registration;

import javax.swing.*;
import java.awt.*;

/**
 * Concrete view of Registration
 */
public class RegistrationFrame extends JFrame implements RegistrationView {

    /**
     * Text field to enter first name, last name, username and passowrd
     */
    private JTextField firstNameTextBox, lastNameTextBox, usernameTextBox,passwordTextBox;

    /**
     * Controller of view
     */
    private RegistrationController controller;

    /**
     * Default constructor
     */
    public RegistrationFrame()
    {
        super("Registration");

        //create model and controller;
        Registration model = new Registration();
        model.addView(this);
        controller = new RegistrationController(model,this);

        //Outer panel directly connected to the frame to display colour
        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setBackground(new Color(0,103,103));

        //main panel for all text field and buttons to be added
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        //create label and text field for first name
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setForeground(Color.WHITE);
        firstNameTextBox = new JTextField();

        //create label and text field for last name
        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setForeground(Color.WHITE);
        lastNameTextBox = new JTextField();

        //create label and text field for username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameTextBox = new JTextField();

        //create label and text field for password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordTextBox = new JTextField();


        JButton registrationBtn = new JButton("Registration");
        registrationBtn.addActionListener(controller);

        //adds label, then empty space, then text field, then another empty space component for first name to main panel
        mainPanel.add(firstNameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(firstNameTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //adds label, then empty space, then text field, then another empty space component for last name to main panel
        mainPanel.add(lastNameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(lastNameTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //adds label, then empty space, then text field, then another empty space component for username to main panel
        mainPanel.add(usernameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(usernameTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //adds label, then empty space, then text field, then another empty space component for password to main panel
        mainPanel.add(passwordLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(passwordTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(registrationBtn);
        colorPanel.add(mainPanel);
        this.add(colorPanel);

        //set common properties for the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);
    }

    /**
     * Gets the input from first name text field
     *
     * @return          String, first name inputted
     */
    public String getFirstName() {
        return firstNameTextBox.getText();
    }


    /**
     * Gets the input from last name text field
     *
     * @return          String, last name inputted
     */
    public String getLastName() {
        return lastNameTextBox.getText();
    }

    /**
     * Gets the input from username text field
     *
     * @return          String, username inputted
     */
    public String getUsername() {
        return usernameTextBox.getText();
    }

    /**
     * Gets the input from password text field
     *
     * @return          String, password inputted
     */
    public String getPassword() {
        return passwordTextBox.getText();
    }

    /**
     * Open dialog box with successful registration message
     */
    @Override
    public void registrationSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully created an account",
                "Registration.Registration Successful",
                JOptionPane.INFORMATION_MESSAGE);

        controller.registrationSuccessful();
    }

    /**
     * Open dialog box with failed registration message
     */
    @Override
    public void registrationFailed() {
        JOptionPane.showMessageDialog(this,
                "Registration.Registration Failed. Please Try Again.",
                "Registration.Registration Failed",
                JOptionPane.ERROR_MESSAGE);
    }
}
