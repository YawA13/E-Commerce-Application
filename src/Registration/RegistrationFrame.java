package Registration;

import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JFrame implements RegistrationView {

    private JTextField firstNameTextBox, lastNameTextBox, usernameTextBox,passwordTextBox;

    private RegistrationController controller;

    public RegistrationFrame()
    {
        super("Registration");

        //create model and controller;
        Registration model = new Registration();
        model.addView(this);
        controller = new RegistrationController(model,this);

        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setBackground(new Color(0,103,103));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        //create label and field for textbox
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setForeground(Color.WHITE);
        firstNameTextBox = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setForeground(Color.WHITE);
        lastNameTextBox = new JTextField();

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameTextBox = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordTextBox = new JTextField();

        JButton registrationBtn = new JButton("Registration");
        registrationBtn.addActionListener(controller);

        //adds all components to frame
        mainPanel.add(firstNameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(firstNameTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(lastNameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(lastNameTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(usernameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(usernameTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(passwordLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(passwordTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(registrationBtn);
        colorPanel.add(mainPanel);
        this.add(colorPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);
    }


    public String getFirstName() {
        return firstNameTextBox.getText();
    }

    public String getLastName() {
        return lastNameTextBox.getText();
    }

    public String getUsername() {
        return usernameTextBox.getText();
    }

    public String getPassword() {
        return passwordTextBox.getText();
    }

    @Override
    public void registrationSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully created an account",
                "Registration.Registration Successful",
                JOptionPane.INFORMATION_MESSAGE);

        controller.registrationSuccessful();
    }

    @Override
    public void registrationFailed() {
        JOptionPane.showMessageDialog(this,
                "Registration.Registration Failed. Please Try Again.",
                "Registration.Registration Failed",
                JOptionPane.ERROR_MESSAGE);
    }
}
