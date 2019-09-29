package hotelbooking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateBooking {
    private int bookingID;
    private String name;
    private int holidayDuration;
    private Date holidayDate;
    private String hotelRoom;
    private float subtotal;
    private float roomPrice;

    public void createBooking() {
        // TODO Functions below
        generateBookingID();
        System.out.println(bookingID);
        getName();
        getHolidayDuration();
        getHolidayDate();
//        getHotelRoom();
//        getSubtotal();
//        getRoomPrice();

//        Booking newBooking = new Booking(bookingID, holidayDuration, hotelRoom, subtotal, roomPrice, name);
    }

    private void getHolidayDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Scanner sc = new Scanner(System.in);

            String day = "";
            String month = "";
            String year = "";

            System.out.println("Please specify the day (1-31) : ");
            while (true) {
                sc = new Scanner(System.in);
                try {
                    day = sc.nextLine();
                    if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
                        System.out.println("Please enter a number between 01-31");
                    } else {
                        if (Integer.parseInt(day) < 10) {
                            day = "0" + Integer.parseInt(day);
                            break;
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a number between 01-31");
                }
            }

            System.out.println("Please specify the month (1-12) : ");
            while (true) {
                sc = new Scanner(System.in);
                try {
                    month = sc.nextLine();
                    if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
                        System.out.println("Please enter a number between 01-12");
                    } else {
                        if (Integer.parseInt(month) < 10) {
                            month = "0" + Integer.parseInt(month);
                            valid = true;
                            break;
                        } else {
                            valid = true;
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a number between 01-31");
                }
            }

            System.out.println("Please specify the year (2000-9999) : ");
            while (true) {
                sc = new Scanner(System.in);
                try {
                    year = sc.nextLine();
                    if (Integer.parseInt(year) < 2000 || Integer.parseInt(year) > 9999) {
                        System.out.println("Please enter a number between 2000-9999");
                    } else {
                        valid = true;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a number between 01-31");
                }
            }
            holidayDate = sdf.parse(day + "-" + month + "-" + year);
        } catch (Exception e){
            System.out.println("ERROR! Holiday parse failed.");
        }
    }

    private void getHolidayDuration() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many days would you like to go away (7 or 14)");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("7")) {
            holidayDuration = 7;
        } else if (choice.equalsIgnoreCase("14")) {
            holidayDuration = 14;
        }
    }

    private void getName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your name : ");
        name = sc.nextLine();
    }

    private void generateBookingID() {
        try {
            File file = new File("bookings.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String currentLine;
            String previousLine;
            while (true) {
                if ((currentLine = br.readLine()) != null) {
                    break;
                }
                previousLine = currentLine;
            }
            String[] values = currentLine.split(",");
            bookingID = Integer.parseInt(values[0]) + 1;
        } catch (Exception e) {
            System.out.println("File could not be found");
        }
    }
}
