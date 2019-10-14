/*
Written By : Robert Watkin
Date Created : 11/10/2019
*/
package hotelbooking;

import javax.swing.*;

public class Window  {

    static JFrame f;
    static boolean loggedIn;
    static JPanel mainMenu;
    static JPanel loginScreen;
    static JPanel createBooking;

    public static void main(String[] args) {
        Window w = new Window();
        w.init();
    }

    private void init() {
        f = new JFrame();
        mainMenu = new GraphicalMainMenu();
        loginScreen = new LoginScreen();
        createBooking = new CreateBooking();

        loggedIn = false;
        mainMenu.setVisible(false);
        loginScreen.setVisible(false);



        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(500,400);
        f.setLocationRelativeTo(null);
        startMainMenu();
    }

    public static void startMainMenu(){
        mainMenu = new GraphicalMainMenu();
        mainMenu.setVisible(true);
        loginScreen.setVisible(false);
        createBooking.setVisible(false);
        f.getContentPane().removeAll();
        f.getContentPane().add(mainMenu);
        f.pack();
        f.setVisible(true);
    }

    public static void startLoginScreen(){
        mainMenu.setVisible(false);
        loginScreen.setVisible(true);
        createBooking.setVisible(false);
        f.getContentPane().removeAll();
        f.getContentPane().add(loginScreen);
        f.pack();
        f.setVisible(true);
    }

    public static void startCreateBookingScreen(){
        createBooking = new CreateBooking();
        mainMenu.setVisible(false);
        loginScreen.setVisible(false);
        createBooking.setVisible(true);
        f.getContentPane().removeAll();
        f.getContentPane().add(createBooking);
        f.pack();
        f.setVisible(true);
    }

}
