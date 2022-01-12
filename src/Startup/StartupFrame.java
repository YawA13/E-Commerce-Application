package Startup;

import Login.Login;

import javax.swing.*;
import java.awt.*;

public class StartupFrame extends JFrame implements  StartupView{

    private StartupController controller;

    public StartupFrame()
    {
        super("Welcome");

        //create model and controller;
        Startup model = new Startup();
        controller = new StartupController(model,this);
        model.addView(this);

        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setBackground(new Color(0,103,103));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JButton registerBtn = new JButton("Register");
        registerBtn.setActionCommand("register");
        registerBtn.addActionListener(controller);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginBtn = new JButton("Login");
        loginBtn.setActionCommand("login");
        loginBtn.addActionListener(controller);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //adds all components to frame
        mainPanel.add(registerBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(loginBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        colorPanel.add(mainPanel);
        this.add(colorPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);

    }

    @Override
    public void register()
    {
        JOptionPane.showMessageDialog(this,
                "We are redirecting you to register",
                "Register",
                JOptionPane.INFORMATION_MESSAGE);

        controller.register();
    }

    @Override
    public void login()
    {
        JOptionPane.showMessageDialog(this,
                "We are redirecting you to login",
                "Login",
                JOptionPane.INFORMATION_MESSAGE);
        controller.login();
    }

    public static void main(String[] args) {
        new StartupFrame();
    }
}
