import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame implements RegisterView{

    private JTextField firstNameTextBox, lastNameTextBox, usernameTextBox,passwordTextBox;

    private RegisterController controller;

    public RegisterFrame()
    {
        super("Register");
        this.setLayout(new GridBagLayout());

        //create model and controller;
        Register model = new Register();
        model.addView(this);
        controller = new RegisterController(model,this);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        //create label and field for textbox
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameTextBox = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameTextBox = new JTextField();

        JLabel usernameLabel = new JLabel("Username");
        usernameTextBox = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        passwordTextBox = new JTextField();

        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(controller);

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

        mainPanel.add(registerBtn);
        this.add(mainPanel);

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
    public void RegisterSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully created an account",
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE);

        controller.registerSuccessful();
    }

    @Override
    public void RegisterFailed() {
        JOptionPane.showMessageDialog(this,
                "Registration Failed. Please Try Again.",
                "Registration Failed",
                JOptionPane.ERROR_MESSAGE);
    }
}
