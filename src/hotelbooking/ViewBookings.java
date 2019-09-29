package hotelbooking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ViewBookings {
    public void printBookings() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║        ALL BOOKINGS        ║");
        System.out.println("║                            ║");
        try {
            File file = new File("bookings.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String[] values;
            while ((line = br.readLine()) != null){
                System.out.println("║                            ║");
                values = line.split(",");

                System.out.println("║ Booking ID - " + values[0] + generateGap(14, values[0]) + "║");
                System.out.println("║ Name - " + values[6] + generateGap(20, values[6]) + "║");
                System.out.println("║ Hotel Room - " + values[2] + generateGap(14, values[2]) + "║");
                System.out.println("║ Duration - " + values[1] + " days " + generateGap(10, values[1]) + "║");
                System.out.println("║ Subtotal - " + values[3] + generateGap(16, values[3]) + "║");
                System.out.println("║ Start Date - " + values[5] + generateGap(14, values[5]) + "║");
                System.out.println("║                            ║");
            }
            System.out.println("==============================");

        } catch (Exception e){
            System.out.println("Bookings file could not be found");
            System.out.println(e.toString());
        }
    }

    private String generateGap(int gap, String value){
        gap -= value.length();
        String gapString = "";
        for (int i = 0; i < gap; i++){
            gapString += " ";
        }
        return gapString;
    }
}
