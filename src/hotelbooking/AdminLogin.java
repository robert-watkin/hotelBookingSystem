package hotelbooking;

import java.util.Scanner;

public class AdminLogin {

    // public login function to log the admin in
    public boolean login(){
        // admin login menu output
        System.out.println("=========Admin Login=========");

        // new scanner object is declared and initialised
        Scanner sc = new Scanner(System.in);

        // prompts to take username and password + takes input
        System.out.println("What is your username? : ");
        String username = sc.nextLine();    // takes user input
        System.out.println("What is your password? : ");
        String password = sc.nextLine();    // takes user input

        // validates the username and password
        if (username.equalsIgnoreCase("Admin") && password.equals("Password1")){
            // if username and password are correct
            return true;
        }
        else {
            // if username and password are incorrect
            System.out.println("incorrect login");
            return false;
        }
    }
}
