/*
Written By : Robert Watkin
Date Created : 14/10/2019
*/
package hotelbooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel implements ActionListener {

    JTextField unField;
    JTextField pwField;

    public LoginScreen (){
        this.setPreferredSize(new Dimension(400,300));
        initLoginComponents();
    }

    private void initLoginComponents() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel innerPanel = new JPanel(new GridLayout(4,2,15,20));

        JLabel unLabel = new JLabel("Username : ");
        JLabel pwLabel = new JLabel("Password : ");
        unField = new JTextField();
        pwField = new JPasswordField();

        unLabel.setVisible(true);
        pwLabel.setVisible(true);
        unField.setVisible(true);
        pwField.setVisible(true);

        unLabel.setPreferredSize(new Dimension(150,20));

        innerPanel.add(unLabel);
        innerPanel.add(unField);
        innerPanel.add(pwLabel);
        innerPanel.add(pwField);
        innerPanel.add(new JPanel());
        innerPanel.add(new JPanel());

        JButton loginButton = new JButton("Login");
        loginButton.setActionCommand("login");
        loginButton.addActionListener(this);

        JButton returnButton = new JButton("Return");
        returnButton.setActionCommand("return");
        returnButton.addActionListener(this);

        innerPanel.add(loginButton);
        innerPanel.add(returnButton);

        add(innerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if("login".equals(actionEvent.getActionCommand())){
            String username = unField.getText();
            String password = pwField.getText();
            if (username.equalsIgnoreCase("admin") && password.equals("Password12")){
                System.out.println("login successful");
                Window.loggedIn = true;
                Window.startMainMenu();
            } else{
                JOptionPane.showMessageDialog(this, "Incorrect Username or Password!");
            }
        } else if ("return".equals(actionEvent.getActionCommand())){
            Window.startMainMenu();
        }
    }
}
