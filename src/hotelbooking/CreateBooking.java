package hotelbooking;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateBooking {
    // VARIABLE DECLARATION
    private Booking newBooking;
    private int bookingID;
    private String name;
    private int holidayDuration;
    private Date holidayDate;
    private String hotelRoom;
    private float subtotal;
    private float roomPrice;

    // primary function to create the bookings
    public void createBooking() {
        // Function calls below to set different values for the booking
        generateBookingID();    // booking ID
        getName();              // Name
        getHolidayDuration();   // Holiday Duration
        getHolidayDate();       // Holiday Date
        getHotelRoom();         // Hotel Room
        getRoomPrice();         // Room Price
        getSubtotal();          // Subtotal

        // creates new booking object
        newBooking = new Booking(bookingID, holidayDuration, hotelRoom, subtotal, roomPrice, name, holidayDate);
        writeToFile();  // calls function to save the new booking to the file
    }

    // this function handles saving new bookings to the file
    private void writeToFile() {
        // new simpledateformat is used to simplify the date for saving
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // concatenates the values together ready to save to file
        String text = newBooking.getBookingID() +
                "," + newBooking.getHolidayDuration() +
                "," + newBooking.getHotelRoom() +
                "," + newBooking.getSubtotal() +
                "," + newBooking.getRoomPrice() +
                "," + sdf.format(newBooking.getHolidayDate()) +
                "," + newBooking.getName();

        // creates a new file object to hold the bookings.txt file
        File bookings = new File("bookings.txt");

        // try catch, to catch any possible exceptions
        try {
            // new bufferedwritter is created for the bookings file
            Writer out = new BufferedWriter(new FileWriter(bookings, true));
            out.append(text + "\n");    // adds the new booking to the text file + starts new line
            out.close();    // closes the bufferedwriter
        } catch (IOException e) {
            // exception thrown if booking could not be saved
            System.out.println("COULD NOT SAVE BOOKING!!");
        }
    }

    // gets the daily price of the room
    private void getRoomPrice() {
        // switch statement checks hotel name to determine the room price
        switch (this.hotelRoom) {
            case "Penthouse":
                roomPrice = 100.00f;
                break;
            case "King Room":
                roomPrice = 90.00f;
                break;
            case "Double Room":
                roomPrice = 80.00f;
                break;
            case "Triple Room":
                roomPrice = 70.00f;
                break;
            case "Quad Room":
                roomPrice = 160.00f;
                break;
            case "Single":
                roomPrice = 50.00f;
                break;
            default:
                // default case for invalid entry
                System.out.println("ERROR! invalid room type");
        }
    }

    private void getSubtotal() {
        subtotal = holidayDuration * roomPrice;
        subtotal = subtotal * (float) 1.025;
    }

    private void getHotelRoom() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║     Select Hotel Room      ║");
        System.out.println("║ 1 - Penthouse              ║");
        System.out.println("║ 2 - King Room              ║");
        System.out.println("║ 3 - Double Room            ║");
        System.out.println("║ 4 - Triple Room            ║");
        System.out.println("║ 5 - Quad Room              ║");
        System.out.println("║ 6 - Single                 ║");
        System.out.println("==============================");

        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (1 <= choice && choice <= 6) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error please enter a valid number (1-6)");
            }
            System.out.println("Error please enter a valid number (1-6)");
        }
        switch (choice) {
            case 1:
                hotelRoom = "Penthouse";
                break;
            case 2:
                hotelRoom = "King Room";
                break;
            case 3:
                hotelRoom = "Double Room";
                break;
            case 4:
                hotelRoom = "Triple Room";
                break;
            case 5:
                hotelRoom = "Quad Room";
                break;
            case 6:
                hotelRoom = "Single";
                break;
            default:
                System.out.println("Invalid menu choice");
                break;
        }
    }

    private void getHolidayDate() {
        try {
            while (true) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Scanner sc = new Scanner(System.in);

                String day = "";
                String month = "";
                String year = "";

                System.out.println("=====Set Holiday Date=====");
                while (true) {
                    sc = new Scanner(System.in);
                    try {
                        System.out.println("Please specify the day (1-31) : ");
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
                                break;
                            } else {
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
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter a number between 01-31");
                    }
                }

                holidayDate = sdf.parse(day + "/" + month + "/" + year);
                Date todaysDate = new Date();
                if (holidayDate.after(todaysDate)){
                    break;
                }else{
                    System.out.println("Your booked date cannot be before today. Try Again!");
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR! Holiday parse failed.");
        }
    }

    private void getHolidayDuration() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many days would you like to go away (7 or 14)");
        while (true) {
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("7")) {
                holidayDuration = 7;
                break;
            } else if (choice.equalsIgnoreCase("14")) {
                holidayDuration = 14;
                break;
            } else {
                System.out.println("Please enter either 7 or 14");
            }
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
            String previousLine = "";
            int counter = 0;
            while (true) {
                if ((currentLine = br.readLine()) == null && counter > 0) {
                    break;
                } else if (currentLine == null && counter == 0) {
                    bookingID = 1;
                    return;
                }
                previousLine = currentLine;
                counter++;
            }
            String[] values = previousLine.split(",");
            bookingID = Integer.parseInt(values[0]) + 1;
        } catch (Exception e) {
            System.out.println("File could not be found");
        }
    }
}
