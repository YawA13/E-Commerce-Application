package Login;

import javax.swing.*;
import java.awt.*;

/**
 * Concrete view of Login
 */
public class LoginFrame extends JFrame implements LoginView{

    /**
     * Text field for username and password
     */
    private JTextField  usernameTextBox, passwordTextBox;

    /**
     * Controller of model and view
     */
    private LoginController controller;

    /**
     * Default constructor
     */
    public LoginFrame()
    {
        super("Login");

        //create model and controller;
        Login model = new Login();
        model.addView(this);
        controller = new LoginController(model,this);

        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setBackground(new Color(0,103,103));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameTextBox = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordTextBox = new JTextField();

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(controller);

        //adds all components to frame
        mainPanel.add(usernameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(usernameTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(passwordLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(passwordTextBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(loginBtn);

        colorPanel.add(mainPanel);
        this.add(colorPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);
    }

    /**
     * Gets the text from username text field
     *
     * @return          String, the username inputted by the customer
     */
    public String getUsername()
    {
        return usernameTextBox.getText();
    }

    /**
     * Gets the text from password text field
     *
     * @return          String, the password inputted by the customer
     */
    public String getPassword()
    {
        return passwordTextBox.getText();
    }

    /**
     * Open dialog box with successful login message and calls loginSuccessful method in the controller
     */
    @Override
    public void loginSuccessful()
    {
        JOptionPane.showMessageDialog(this,
                "You have successfully logged into the store",
                "Login.Login Successful",
                JOptionPane.INFORMATION_MESSAGE);

        controller.loginSuccessful();
    }

    /**
     * Open dialog box with failed login message
     */
    @Override
    public void loginFailed()
    {
        JOptionPane.showMessageDialog(this,
                "Incorrect username or password",
                "Login.Login Failed",
                JOptionPane.ERROR_MESSAGE);

    }
}
