package hotelbooking;

import com.sun.tools.javac.Main;

import java.util.Scanner;

public class MainMenu {
    // VARIABLE DECLARATION
    private boolean adminLoggedIn;
    private int choice;

    // MAIN FUNCTION - ENTRY POINT FOR PROGRAM
    public static void main(String[] args) {
        MainMenu mm = new MainMenu(false);
        mm.startMenu();
    }

    // CONSTRUCTOR
    public MainMenu(boolean adminLoggedIn) {
        this.adminLoggedIn = adminLoggedIn;
    }

    // the function below can be called from outside the class to start the menu
    public void startMenu(){
        displayMenu();
    }

    // this function displays the relevant menu to the user
    private void displayMenu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║          MAIN MENU         ║");
        System.out.println("║ 1 - Create Booking         ║");

        // checks if the user is not an admin
        if (!adminLoggedIn){
            // if not then the admin login option is displayed
            System.out.println("║ 2 - Admin Login            ║");
        } else {
            // else (if they're admin), the admin options are displayed
            System.out.println("║        Admin Options       ║");
            System.out.println("║ 2 - View Bookings          ║");
            System.out.println("║ 3 - Logout                 ║");
        }
        // quit option displayed
        System.out.println("║ 0 - Quit                   ║");
        System.out.println("==============================");
        menuChoice();   // calls function to take user input for the menu
    }

    // This functions deals with the users menu choice
    private void menuChoice(){
        int maxChoice;  // new integer is created to hold the maximum menu options

        // checks if admin is logged in
        if (adminLoggedIn){
            // if admin, the maximum menu choice is 3
            maxChoice = 3;
        }else{
            // if not admin, the maximum menu choice is 2
            maxChoice = 2;
        }

        // new Scanner object declared
        Scanner sc;
        while (true) {
            // Scanner initialised each loop.
            // this was done due to an error where inputs were not being waited for
            // after the first loop occured
             sc = new Scanner(System.in);

             // try to catch exceptions
            try {
                choice = Integer.parseInt(sc.nextLine());   // takes users choice, attempts to parse to integer

                // ensures the users choice is in range
                if (0 <= choice && choice <= maxChoice){
                    // if in range, break loop
                    break;
                } else {
                    // error message if the number is not in range
                    System.out.println("Error please enter a valid number (0-" + maxChoice + ")");
                    // ** Loop will continue **
                }
            } catch (Exception e) {
                // error message if non integer has been inputted
                System.out.println("Error please enter a valid number (0-" + maxChoice + ")");
            }
        } // end of loop

        processChoice();
    }

    // function processes the users input to determine what happens next
    private void processChoice(){

        // switch statement to process the users choice
        switch (choice){
            case 0:
                // exits the application
                System.exit(0);
                break;
            case 1:
                // creates a new instance of the create booking class
                CreateBooking cb = new CreateBooking();
                cb.createBooking(); // calls the create booking method
                startMenu();        // calls back to the main menu start
                break;
            case 2:
                // if statement checks if an admin is logged in
                if (adminLoggedIn){
                    // if admin is logged in
                    // creates a new instance of the view booking class
                    ViewBookings vb = new ViewBookings();
                    vb.printBookings(); // calls the print booking function
                } else {
                    // if admin is not logged in
                    // creates a new instance of admin login
                    AdminLogin al = new AdminLogin();
                    adminLoggedIn = al.login(); // sets adminLoggedIn to the return of the login function
                }
                startMenu();    // calls the start of the menu
                break;
            case 3:
                logout();   // calls the logout function
                startMenu();    // calls the start of the menu
                break;
            default:
                // console output for invalid input
                System.out.println("Menu choice invalid");
        }
    }

    // the function below logs out the currently logged in admin
    private void logout() {
        // creates a new scanner object
        Scanner sc = new Scanner(System.in);
        // asks the user for input if they want to log in
        System.out.println("Are you sure? (y/n) : ");

        // checks if the input is equal to 'y' (yes)
        if (sc.nextLine().equalsIgnoreCase("y")){
            adminLoggedIn = false;  // sets adminLoggedIn to false
        }
    }
}
