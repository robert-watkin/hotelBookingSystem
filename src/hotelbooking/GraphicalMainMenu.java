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
    JLabel title;
    private JButton createBooking;
    private JButton adminLogin;
    private JButton viewBookings;
    private JButton logout;
    private JButton quit;
    private boolean loggedIn;
    int rows;

    public GraphicalMainMenu (){
        initMainMenuButtons();
    }

    private void initMainMenuButtons() {
        if (Window.loggedIn) {rows = 3;}
        else {rows = 2;}
        this.setPreferredSize(new Dimension(300,150*rows));
        JPanel innerPanel = new JPanel(new GridLayout(rows,0,0,40));

        createBooking = new JButton();
        adminLogin = new JButton();
        viewBookings = new JButton();
        logout = new JButton();
        quit = new JButton();

        createBooking.setActionCommand("create booking");
        adminLogin.setActionCommand("admin login");
        viewBookings.setActionCommand("view bookings");
        logout.setActionCommand("logout");
        createBooking.setPreferredSize(new Dimension(150,75));

        createBooking.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewBookings.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);



        createBooking.setVisible(true);
        if (!Window.loggedIn) {
            adminLogin.setVisible(true);
            viewBookings.setVisible(false);
            logout.setVisible(false);
        }else if (Window.loggedIn){
            adminLogin.setVisible(false);
            viewBookings.setVisible(true);
            logout.setVisible(true);
        }
        createBooking.setText("Create Booking");
        adminLogin.setText("Admin Login");
        viewBookings.setText("View Bookings");
        logout.setText("Logout");

        createBooking.addActionListener(this);
        adminLogin.addActionListener(this);
        viewBookings.addActionListener(this);
        logout.addActionListener(this);

        innerPanel.add(createBooking);
        if (!Window.loggedIn) {
            innerPanel.add(adminLogin);
        }else {
            innerPanel.add(viewBookings);
            innerPanel.add(logout);
        }

        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(innerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("create booking".equals(actionEvent.getActionCommand())) {
            System.out.println("Create Booking");
            Window.startCreateBookingScreen();
        }
        if ("admin login".equals(actionEvent.getActionCommand())) {
            System.out.println("Admin Login");
            Window.startLoginScreen();
        }
        if ("view bookings".equals(actionEvent.getActionCommand())) {
            System.out.println("View Booking");
        }
        if ("logout".equals(actionEvent.getActionCommand())) {
            System.out.println("Logout");
            Window.loggedIn = false;
            Window.startMainMenu();
        }
    }
}
