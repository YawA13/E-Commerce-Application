package Login;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame implements LoginView{

    private JTextField  usernameTextBox, passwordTextBox;

    private LoginController controller;

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

    public String getUsername()
    {
        return usernameTextBox.getText();
    }

    public String getPassword()
    {
        return passwordTextBox.getText();
    }

    @Override
    public void loginSuccessful()
    {
        JOptionPane.showMessageDialog(this,
                "You have successfully logged into the store",
                "Login.Login Successful",
                JOptionPane.INFORMATION_MESSAGE);

        controller.loginSuccessful();
    }

    @Override
    public void loginFailed()
    {
        JOptionPane.showMessageDialog(this,
                "Incorrect username or password",
                "Login.Login Failed",
                JOptionPane.ERROR_MESSAGE);

    }
}
