package Startup;

import Login.Login;

import javax.swing.*;
import java.awt.*;

public class StartupFrame extends JFrame {



    public StartupFrame()
    {
        super("Welcome");
        this.setLayout(new GridBagLayout());

        //create model and controller;
        Startup model = new Startup();
        StartupController controller = new StartupController(model,this);
        model.addView(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JButton registerBtn = new JButton("Register");
        registerBtn.setActionCommand("register");
        registerBtn.addActionListener(controller);

        JButton loginBtn = new JButton("Login");
        loginBtn.setActionCommand("login");
        loginBtn.addActionListener(controller);

        //adds all components to frame
        mainPanel.add(registerBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(loginBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setPreferredSize(new Dimension(600,300));
        this.setVisible(true);

    }
}
