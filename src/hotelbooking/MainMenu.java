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

    public void startMenu(){
        displayMenu();
    }

    private void displayMenu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║          MAIN MENU         ║");
        System.out.println("║ 1 - Create Booking         ║");
        if (!adminLoggedIn){
            System.out.println("║ 2 - Admin Login            ║");
        } else {
            System.out.println("║        Admin Options       ║");
            System.out.println("║ 2 - View Bookings          ║");
            System.out.println("║ 3 - Logout                 ║");
        }
        System.out.println("║ 0 - Quit                   ║");
        System.out.println("==============================");
        menuChoice();
    }

    private void menuChoice(){
        int maxChoice;
        if (adminLoggedIn){
            maxChoice = 3;
        }else{
            maxChoice = 2;
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (0 <= choice && choice <= maxChoice){
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error please enter a valid number (0-" + maxChoice + ")");
            }
            System.out.println("Error please enter a valid number (0-" + maxChoice + ")");
        }
        processChoice();
    }

    private void processChoice(){
        switch (choice){
            case 0:
                System.exit(0);
                break;
            case 1:
                // TODO create booking
                CreateBooking cb = new CreateBooking();
                cb.createBooking();
                break;
            case 2:
                if (adminLoggedIn){
                    // TODO view bookings
                    ViewBookings vb = new ViewBookings();
                    vb.printBookings();
                } else {
                    AdminLogin al = new AdminLogin();
                    adminLoggedIn = al.login();
                }
                startMenu();
                break;
            case 3:
                logout();
                startMenu();
                break;
            default:
                System.out.println("Menu choice invalid");
        }
    }

    private void logout() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure? (y/n) : ");
        if (sc.nextLine().equalsIgnoreCase("y")){
            adminLoggedIn = false;
        }
    }
}
