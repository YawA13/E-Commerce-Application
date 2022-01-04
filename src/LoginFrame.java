import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame implements LoginView{

    private JTextField  usernameTextBox, passwordTextBox;

    private LoginController controller;

    public LoginFrame()
    {
        super("Login");
        this.setLayout(new GridBagLayout());

        //create model and controller;
        Login model = new Login();
        model.addView(this);
        controller = new LoginController(model,this);

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

    @Override
    public void loginSuccessful()
    {
        JOptionPane.showMessageDialog(this,
                "You have successfully logged into the store",
                "Login Successful",
                JOptionPane.INFORMATION_MESSAGE);

        controller.loginSuccessful();
    }

    @Override
    public void loginFailed()
    {
        JOptionPane.showMessageDialog(this,
                "Incorrect username or password",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);

    }
}
