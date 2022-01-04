import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField  usernameTextBox, passwordTextBox;

    public LoginFrame()
    {
        super("Login");
        this.setLayout(new GridBagLayout());

        //create model and controller;
        Login model = new Login();
        LoginController controller = new LoginController(model,this);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username");
        usernameTextBox = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
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
        this.add(mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);
    }

    public String getUsername()
    {
        return usernameTextBox.getText();
    }

    public String getPassword()
    {
        return passwordTextBox.getText();
    }
}
