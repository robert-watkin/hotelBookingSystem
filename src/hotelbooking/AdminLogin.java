package hotelbooking;

import java.util.Scanner;

public class AdminLogin {
    private String username;
    private String password;

    public boolean login(){
        System.out.println("=========Admin Login=========");
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your username? : ");
        username = sc.nextLine();
        System.out.println("What is your password? : ");
        password = sc.nextLine();

        if (username.equalsIgnoreCase("Admin") && password.equals("Password1")){
            return true;
        }
        else {
            System.out.println("incorrect login");
            return false;
        }
    }
}
