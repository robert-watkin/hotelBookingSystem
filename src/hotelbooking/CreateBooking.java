package hotelbooking;

import javax.naming.event.ObjectChangeListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateBooking extends JPanel implements ActionListener, ItemListener {
    // VARIABLE DECLARATION
    private Booking newBooking;
    private int bookingID;
    private Date holidayDate;
    private float subtotal;
    private float roomPrice;

    JPanel innerPanel;
    JTextField nameField;
    JComboBox durationField;
    JComboBox day;
    JComboBox month;
    JComboBox year;
    JComboBox hotelRoomField;
    JLabel subtotalText;
    JLabel roomPriceText;
    JButton confirmButton;
    JButton returnButton;


    public CreateBooking() {
        setPreferredSize(new Dimension(500, 430));
        initCreateBooking();
    }

    private void initCreateBooking() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING, -150, 5));

        roomPrice = 0;
        subtotal = 0;

        JLabel idLabel = new JLabel("ID : ");
        idLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel id = new JLabel();

        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);
        nameLabel.setPreferredSize(new Dimension(50, 25));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 25));

        Integer[] durations = {7, 14};
        JLabel durationLabel = new JLabel("Duration : ");
        durationLabel.setHorizontalAlignment(JLabel.RIGHT);
        durationField = new JComboBox(durations);
        durationField.addItemListener(this);

        String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] years = {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
        JLabel dateLabel = new JLabel("Start Date :");
        dateLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel daysLabel = new JLabel("days : ");
        daysLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel monthsLabel = new JLabel("months : ");
        monthsLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel yearsLabel = new JLabel("years : ");
        yearsLabel.setHorizontalAlignment(JLabel.RIGHT);
        day = new JComboBox(days);
        month = new JComboBox(months);
        year = new JComboBox(years);

        generateBookingID();
        id.setText(Integer.toString(bookingID));

        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(10, 2, 15, 15));

        innerPanel.add(idLabel);
        innerPanel.add(id);

        innerPanel.add(nameLabel);
        innerPanel.add(nameField);

        innerPanel.add(durationLabel);
        innerPanel.add(durationField);

        JPanel dayPanel = new JPanel(new GridLayout(0, 2, 0, 0));
        dayPanel.add(daysLabel);
        dayPanel.add(day);

        JPanel monthPanel = new JPanel(new GridLayout(0, 2, 0, 0));
        monthPanel.add(monthsLabel);
        monthPanel.add(month);

        JPanel yearPanel = new JPanel(new GridLayout(0, 2, 0, 0));
        yearPanel.add(yearsLabel);
        yearPanel.add(year);

        String[] hotelRooms = {"Penthouse", "King Room", "Double Room", "Triple Room", "Quad Room", "Single"};
        JLabel hotelRoomLabel = new JLabel("Hotel Room:");
        hotelRoomLabel.setHorizontalAlignment(JLabel.RIGHT);
        hotelRoomField = new JComboBox(hotelRooms);
        hotelRoomField.addItemListener(this);

        JLabel roomPriceLabel = new JLabel("Room Price :");
        roomPriceLabel.setHorizontalAlignment(JLabel.RIGHT);
        roomPriceText = new JLabel(Float.toString(this.roomPrice));

        JLabel subtotalLabel = new JLabel("Subtotal :");
        subtotalLabel.setHorizontalAlignment(JLabel.RIGHT);
        subtotalText = new JLabel(Float.toString(this.subtotal));

        innerPanel.add(dateLabel);
        innerPanel.add(dayPanel);

        innerPanel.add(new JPanel());
        innerPanel.add(monthPanel);

        innerPanel.add(new JPanel());
        innerPanel.add(yearPanel);

        innerPanel.add(hotelRoomLabel);
        innerPanel.add(hotelRoomField);

        innerPanel.add(roomPriceLabel);
        innerPanel.add(roomPriceText);

        innerPanel.add(subtotalLabel);
        innerPanel.add(subtotalText);

        confirmButton = new JButton("Confirm Booking");
        returnButton = new JButton("Return");
        confirmButton.addActionListener(this);
        returnButton.addActionListener(this);
        confirmButton.setActionCommand("confirm");
        returnButton.setActionCommand("return");

        JPanel buttons = new JPanel(new GridLayout(0, 2, 10, 0));
        buttons.add(confirmButton);
        buttons.add(returnButton);

        innerPanel.add(new JPanel());
        innerPanel.add(buttons);

        this.add(innerPanel);
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
        switch ((String) this.hotelRoomField.getSelectedItem()) {
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
        Object o = durationField.getSelectedItem();
        Integer duration = (Integer) o;
        subtotal = duration * roomPrice;
        subtotal = subtotal * (float) 1.025;
        System.out.println(subtotal);
    }


    private void setHolidayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String textDate = (String) day.getSelectedItem() + "/" + (String) month.getSelectedItem()+ "/" + (String) year.getSelectedItem();
        System.out.println(textDate);
        try {
            holidayDate = sdf.parse(textDate);
        } catch (Exception e){
            System.out.println("PARSE ERROR");
        }
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("confirm".equals(actionEvent.getActionCommand())) {
            setHolidayDate();
            newBooking = new Booking(bookingID, (Integer) durationField.getSelectedItem(), (String) hotelRoomField.getSelectedItem(), subtotal, roomPrice, nameField.getText(), holidayDate);
            writeToFile();  // calls function to save the new booking to the file
            Window.startMainMenu();
        } else if ("return".equals(actionEvent.getActionCommand())) {
            Window.startMainMenu();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        System.out.println("change detected");
        getRoomPrice();
        getSubtotal();
        roomPriceText.setText(Float.toString(roomPrice));
        subtotalText.setText(Float.toString(subtotal));
    }
}
