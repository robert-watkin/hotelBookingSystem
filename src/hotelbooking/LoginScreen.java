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
    // VARIABLE DECLARATION
    private JTextField unField;
    private JTextField pwField;
    // END OF VARIABLE DECLARATION

    // Constructor
    public LoginScreen (){
        // sets JPanel size
        setPreferredSize(new Dimension(400,300));
        initLoginComponents();  // calls function to initialise components
    }

    // function to initialise panel components
    private void initLoginComponents() {
        this.setBackground(Window.backgroundColor);
        // sets the layout of the JPanel
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        // two new JPanels are created to hold the components - used for the layout
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel innerPanel = new JPanel(new GridLayout(4,2,15,20));

        // creates new label for the menu title
        // VARIABLE DECLARATION
        JLabel title = new JLabel("Admin Login");
        add(title);

        // creates labels to indicate which date the user needs to input into boxes
        JLabel unLabel = new JLabel("Username : ");
        JLabel pwLabel = new JLabel("Password : ");

        // creates to fields to take the users input
        unField = new JTextField();
        pwField = new JPasswordField();

        // sets all to visible
        unLabel.setVisible(true);
        pwLabel.setVisible(true);
        unField.setVisible(true);
        pwField.setVisible(true);

        // sets size of the username label - due to being in a grid layout, all components are set to this size
        unLabel.setPreferredSize(new Dimension(150,20));

        // adds all components to the innerpanel
        innerPanel.add(unLabel);
        innerPanel.add(unField);
        innerPanel.add(pwLabel);
        innerPanel.add(pwField);
        // adds two blank JPanels for spacing
        JPanel e1 = new JPanel();
        JPanel e2 = new JPanel();
        e1.setBackground(Window.backgroundColor);
        e2.setBackground(Window.backgroundColor);
        innerPanel.add(e1);
        innerPanel.add(e2);

        // new JButton is created to log the user in
        JButton loginButton = new JButton("Login");
        loginButton.setActionCommand("login");  // sets the buttons command which will be used when the button is pressed
        loginButton.addActionListener(this);    // adds this class as an actionlistener for the button

        // new JButton is created to return the user to the main menu
        JButton returnButton = new JButton("Return");
        returnButton.setActionCommand("return");    // sets the buttons command which will be used when the button is pressed
        returnButton.addActionListener(this);   // adds this class as an actionlistener for the button

        // adds both buttons to the innerpanel
        innerPanel.add(loginButton);
        innerPanel.add(returnButton);

        // sets the panels background color based on the global static variable in the Window class
        outerPanel.setBackground(Window.backgroundColor);
        innerPanel.setBackground(Window.backgroundColor);

        // adds the innerpanel to the outerpanel
        outerPanel.add(innerPanel);
        add(outerPanel);    // adds the outerpanel to the overall class which extends JPanel - therefore the class is a JPanel
    }

    /*
    The function below is called when any button is pressed
    The function takes an actionevent which is essentially the button which has been pressed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if statements used to check the buttons command
        // this helps the program know which button has been pressed
        if("login".equals(actionEvent.getActionCommand())){
            // if the login button has been pressed
            String username = unField.getText();    // set username to the text within the unField
            String password = pwField.getText();    // sets password to the text within the pwField
            if (username.equalsIgnoreCase("admin") && password.equals("Password12")){
                // if both username and password are correct
                System.out.println("login successful"); // console output for debugging
                Window.loggedIn = true;     // set loggedIn boolean to true - can be accessed anywhere in the program
                Window.startMainMenu();     // starts the mainmenu from within the Window class
            } else{
                // else - show a dialog box to indicate incorrect credentials
                JOptionPane.showMessageDialog(this, "Incorrect Username or Password!");
            }
        } else if ("return".equals(actionEvent.getActionCommand())){
            // if the return button has been pressed
            Window.startMainMenu(); // starts the main menu from within the Window class (loggedIn is still false)
        }
    }
}
