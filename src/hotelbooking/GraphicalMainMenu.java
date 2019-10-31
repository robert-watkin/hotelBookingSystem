/*
Written By : Robert Watkin
Date Created : 11/10/2019
*/
package hotelbooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalMainMenu extends JPanel implements ActionListener {

    // Constructor
    public GraphicalMainMenu (){
        initMainMenuButtons();
    }

    // The function below initialises the components within the main menu
    private void initMainMenuButtons() {


        this.setBackground(Window.backgroundColor);
        // Declares integer for the rows of the grid layout
        // This will change as there will be more buttons required for a loggedin user
        int rows;
        if (Window.loggedIn) {
            // if loggedIn
            rows = 4;   // set rows to 4
        }
        else {
            // else
            rows = 3;   // set rows to 3
        }
        // sets the size of the window
        this.setPreferredSize(new Dimension(300,120* rows));

        // creates the innerpanel with 'rows' as the number of rows
        JPanel innerPanel = new JPanel(new GridLayout(rows,0,0,40));
        innerPanel.setBackground(Window.backgroundColor);
        JLabel title = new JLabel("Holiday Booking System");   // creates a JLabel
        innerPanel.add(title);

        // main menu buttons are declared and initialised
        JButton createBooking = new JButton();
        JButton adminLogin = new JButton();
        JButton viewBookings = new JButton();
        JButton logout = new JButton();

        // action commands for all buttons are added - this lets the program differentiate each button
        createBooking.setActionCommand("create booking");
        adminLogin.setActionCommand("admin login");
        viewBookings.setActionCommand("view bookings");
        logout.setActionCommand("logout");

        // sets the size of the create booking button - due to being in a gridlayout, all components in the grid will have this layout
        createBooking.setPreferredSize(new Dimension(150,75));

        // button alignement is set so they are all centered
        createBooking.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewBookings.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);


        // text for all buttons is set
        createBooking.setText("Create Booking");
        adminLogin.setText("Admin Login");
        viewBookings.setText("View Bookings");
        logout.setText("Logout");

        // this class is set to all buttons ActionListener - this class implements ActionListener
        createBooking.addActionListener(this);
        adminLogin.addActionListener(this);
        viewBookings.addActionListener(this);
        logout.addActionListener(this);

        // create booking visibility is set to true
        createBooking.setVisible(true);


        // if statement checks if the admin is logged in to determine which buttons should be shown
        if (!Window.loggedIn) {
            // if not logged in
            adminLogin.setVisible(true);        // login button is visible
            viewBookings.setVisible(false);     // view bookings is not visible
            logout.setVisible(false);           // logout button is not visible
        }else if (Window.loggedIn){
            // if logged in
            adminLogin.setVisible(false);       // login button is not visible
            viewBookings.setVisible(true);      // viewbookings button is visble
            logout.setVisible(true);            // logout button is visible
        }

        // buttons are added to innerPanel depending if the admin is logged in
        innerPanel.add(createBooking);
        if (!Window.loggedIn) {
            // if not logged in
            innerPanel.add(adminLogin);     // add admin login button
        }else {
            // if logged in
            innerPanel.add(viewBookings);   // add view bookings button
            innerPanel.add(logout);         // add logout button
        }

        // sets the layout of this class (JPanel) to a centered flowlayout
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(innerPanel);
    }

    /*
    the function below is called whenever a button is pressed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("create booking".equals(actionEvent.getActionCommand())) {
            // if the create booking button is pressed
            System.out.println("Create Booking");   // console output for debuggin
            Window.startCreateBookingScreen();      // create booking screen is called
        }
        if ("admin login".equals(actionEvent.getActionCommand())) {
            // if the admin login button is pressed
            System.out.println("Admin Login");      // console output for debuggin
            Window.startLoginScreen();              // login screen is called
        }
        if ("view bookings".equals(actionEvent.getActionCommand())) {
            // if the view bookings button is pressed
            System.out.println("View Booking");     // console output for debuggin
            Window.startViewBookingScreen();        // view booking screen is called
        }
        if ("logout".equals(actionEvent.getActionCommand())) {
            // if the logout button is pressed
            System.out.println("Logout");           // console output for debuggin
            Window.loggedIn = false;                // loggedIn variable is set to false
            Window.startMainMenu();                 // main menu is called
        }
    }
}
