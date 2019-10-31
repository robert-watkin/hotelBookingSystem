package hotelbooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateBooking extends JPanel implements ActionListener, ItemListener {
    // VARIABLE DECLARATION
    private Booking newBooking;
    private int bookingID;
    private Date holidayDate;
    private float subtotal;
    private float roomPrice;
    private float cardFee;

    private JPanel innerPanel;
    private JTextField nameField;
    private JComboBox durationField;
    private JComboBox day;
    private JComboBox month;
    private JComboBox year;
    private JComboBox hotelRoomField;
    private JLabel subtotalText;
    private JLabel roomPriceText;
    private JButton confirmButton;
    private JButton returnButton;
    // END OF VARIABLE DECLARATION

    // Constructor
    public CreateBooking() {
        // sets size of window
        setPreferredSize(new Dimension(500, 430));
        initCreateBooking();    // initialises the create booking window
    }

    // the funciton below initialises all components of the createBooking window
    private void initCreateBooking() {
        // sets the layout for the class (extends JPanel) to a new flowlayout
        this.setLayout(new FlowLayout(FlowLayout.LEADING, -150, 5));

        // initialises the roomprice and subtotal to 0
        roomPrice = 0;
        subtotal = 0;

        // ---------------ID---------------
        // Creates new JLabel to identify the ID field
        JLabel idLabel = new JLabel("ID : ");
        idLabel.setHorizontalAlignment(JLabel.RIGHT);   // sets alignment for the JLabel
        JLabel id = new JLabel();  // creates a new JLabel to hold the new ID

        // calls function to generate the booking ID
        generateBookingID();
        id.setText(Integer.toString(bookingID));    // sets id label to the generated bookingID



        // ---------------NAME---------------
        // creates new JLabel to identify the name field
        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);                 // sets alignment for the JLabel
        nameLabel.setPreferredSize(new Dimension(50, 25));// sets the size of the JLabel
        nameField = new JTextField();                                   // creates a new JTextField for the name
        nameField.setPreferredSize(new Dimension(200, 25));// sets the size of the JLabel



        // ---------------DURATION---------------
        Integer[] durations = {7, 14};  // creates a new array for the duration options
        // creates new JLabel to identify the duration field
        JLabel durationLabel = new JLabel("Duration : ");
        durationLabel.setHorizontalAlignment(JLabel.RIGHT); // sets the alignment of the JLabel
        durationField = new JComboBox(durations);   // initialises the duration combobox with the options in durations
        durationField.addItemListener(this);    // adds this class as an item listener for durationfield



        // ---------------DATE---------------
        // days, months, and years, arrays declared and initialised for the date options
        String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] years = {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
        // creates new JLabel to identify the date fields
        JLabel dateLabel = new JLabel("Start Date :");
        dateLabel.setHorizontalAlignment(JLabel.RIGHT);
        // creates new JLabel to identify the days field
        JLabel daysLabel = new JLabel("days : ");
        daysLabel.setHorizontalAlignment(JLabel.RIGHT);     // sets alignment for the JLabel
        // creates new JLabel to identify the months field
        JLabel monthsLabel = new JLabel("months : ");
        monthsLabel.setHorizontalAlignment(JLabel.RIGHT);   // sets alignment for the JLabel
        // creates new JLabel to identify the years field
        JLabel yearsLabel = new JLabel("years : ");
        yearsLabel.setHorizontalAlignment(JLabel.RIGHT);    // sets alignment for the JLabel

        // initialises the comboBox's with the values in the days, months, years arrays
        day = new JComboBox(days);
        month = new JComboBox(months);
        year = new JComboBox(years);
        // adds this class as an item listener for the day, month & year
        day.addItemListener(this);
        month.addItemListener(this);
        year.addItemListener(this);

        // new panel created to hold the days
        JPanel dayPanel = new JPanel(new GridLayout(0, 2, 0, 0));
        // adds day details to daypanel
        dayPanel.add(daysLabel);
        dayPanel.add(day);

        // new panel created to hold the months
        JPanel monthPanel = new JPanel(new GridLayout(0, 2, 0, 0));
        // adds month details to monthpanel
        monthPanel.add(monthsLabel);
        monthPanel.add(month);

        // new panel created to hold the years
        JPanel yearPanel = new JPanel(new GridLayout(0, 2, 0, 0));
        // adds year details to yearpanel
        yearPanel.add(yearsLabel);
        yearPanel.add(year);

        // new JPanel created to hold all components made above
        innerPanel = new JPanel();
        // layout for inner panel set to hold all components created above
        innerPanel.setLayout(new GridLayout(10, 2, 15, 15));



        // ---------------HOTEL ROOM---------------
        // creates string array to hold the different hotel rooms
        String[] hotelRooms = {"Penthouse", "King Room", "Double Room", "Triple Room", "Quad Room", "Single"};
        // creates new JLabel to identify the hotel room field
        JLabel hotelRoomLabel = new JLabel("Hotel Room:");
        hotelRoomLabel.setHorizontalAlignment(JLabel.RIGHT);    // sets alignment for the JLabel
        hotelRoomField = new JComboBox(hotelRooms);            // initialises combo box with the calues in hotelRooms
        hotelRoomField.addItemListener(this);   // adds this class as an item listener for hotelRoomField


        // ---------------ROOM PRICE---------------
        JLabel roomPriceLabel = new JLabel("Room Price :");
        roomPriceLabel.setHorizontalAlignment(JLabel.RIGHT);
        roomPriceText = new JLabel(Float.toString(this.roomPrice));

        // ---------------SUBTOTAL---------------
        JLabel subtotalLabel = new JLabel("Subtotal :");
        subtotalLabel.setHorizontalAlignment(JLabel.RIGHT);
        subtotalText = new JLabel(Float.toString(this.subtotal));

        // ---------------BUTTONS---------------
        // creates two buttons (confirm, return)
        confirmButton = new JButton("Confirm Booking");
        returnButton = new JButton("Return");
        // adds this class as an ActionListener for the two buttons
        confirmButton.addActionListener(this);
        returnButton.addActionListener(this);
        // sets ActionCommands for the buttons so they can be differentiated
        confirmButton.setActionCommand("confirm");
        returnButton.setActionCommand("return");

        // new panel to hold the buttons is created
        JPanel buttons = new JPanel(new GridLayout(0, 2, 10, 0));
        // buttons are added to the panel
        buttons.add(confirmButton);
        buttons.add(returnButton);

        // adds ID details
        innerPanel.add(idLabel);
        innerPanel.add(id);

        // adds name details
        innerPanel.add(nameLabel);
        innerPanel.add(nameField);

        // adds duration details
        innerPanel.add(durationLabel);
        innerPanel.add(durationField);

        // adds date details
        innerPanel.add(dateLabel);
        innerPanel.add(dayPanel);
        innerPanel.add(new JPanel());
        innerPanel.add(monthPanel);
        innerPanel.add(new JPanel());
        innerPanel.add(yearPanel);

        // adds hotel room details
        innerPanel.add(hotelRoomLabel);
        innerPanel.add(hotelRoomField);

        // adds room price details
        innerPanel.add(roomPriceLabel);
        innerPanel.add(roomPriceText);

        // adds subtotal details
        innerPanel.add(subtotalLabel);
        innerPanel.add(subtotalText);

        // buttons are added to the panel
        innerPanel.add(new JPanel());
        innerPanel.add(buttons);

        // inner panel is added to this class (extends JPanel)
        this.add(innerPanel);

        // price is updated
        updatePrice();
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
                cardFee = 4.40f;
                break;
            case "King Room":
                roomPrice = 90.00f;
                cardFee = 2.00f;
                break;
            case "Double Room":
                roomPrice = 80.00f;
                cardFee = 1.80f;
                break;
            case "Triple Room":
                roomPrice = 70.00f;
                cardFee = 1.80f;
                break;
            case "Quad Room":
                roomPrice = 160.00f;
                cardFee = 1.80f;
                break;
            case "Single":
                roomPrice = 50.00f;
                cardFee = 1.80f;
                break;
            default:
                // default case for invalid entry
                System.out.println("ERROR! invalid room type");
        }
    }

    // the function below calculates the subtotal for the booking
    private void getSubtotal() {
        // object below is created to hold the duration field selection
        Object o = durationField.getSelectedItem();
        // o is converted to an integer (7 or 14)
        Integer duration = (Integer) o;

        // VAT float is created with the value 1.025
        float VAT = (float) 1.025;

        // subtotal is calculated
        subtotal = ((duration * roomPrice) * VAT) + cardFee;
        System.out.println(subtotal); // console output for debugging
    }

    // the function below sets the date variable based on the users choice
    private void setHolidayDate() {
        // a new date format is created     dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // a new Sting is created in the same format as the date format
        String textDate = (String) day.getSelectedItem() + "/" + (String) month.getSelectedItem()+ "/" + (String) year.getSelectedItem();
        System.out.println(textDate);   // console output for debugging
        // try, catch block in case of parse exceptions
        try {
            // holiday date is set to the textDate parsed by the SimpleDateFormat (sdf)
            holidayDate = sdf.parse(textDate);
        } catch (Exception e){
            // console output incase of exception.
            System.out.println("PARSE ERROR");
        }
    }

    // the function below generates the next booking ID
    private void generateBookingID() {
        // try, catch block incase of file exceptions
        try {
            // new file object is created for the file bookings.txt
            File file = new File("bookings.txt");
            // new buffered reader is created to read the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            String currentLine; // currentline String is created to hold the current line from the text file
            String previousLine = "";   // previous line is created and initialised empty
            int counter = 0;    // counter is set to 0
            // infinite loop
            while (true) {

                if ((currentLine = br.readLine()) == null && counter > 0) {
                    // if the current line is empty and the counter is more than 0
                    break;  // break the loop
                } else if (currentLine == null && counter == 0) {
                    // if the current line is empty and the counter IS 0
                    bookingID = 1; // set the bookingID to 1
                    return; // return to function call
                }
                previousLine = currentLine; // set the previous line to the current line
                counter++;  // increment the counter
            }
            // new array created called values and is set to the information on the previousline split by ','
            String[] values = previousLine.split(",");
            // booking ID is then set to the final value in values (the previous booking ID) +1
            bookingID = Integer.parseInt(values[0]) + 1;
        } catch (Exception e) {
            // console output if an exception occurs
            System.out.println("File could not be found");
        }
    }

    // the function below updates the price fields
    private void updatePrice() {
        // functions calls to update price values
        getRoomPrice();
        getSubtotal();
        // JLabel text set to newly updated values
        roomPriceText.setText(Float.toString(roomPrice));
        subtotalText.setText(Float.toString(subtotal));

        System.out.println("price updated"); // console output for debugging
    }

    /*
    the function below is called whenever a button is pressed in this class
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("confirm".equals(actionEvent.getActionCommand())) {
            // if the confirm button has been pressed
            setHolidayDate();   // set the holiday date
            // create a new booking object with the values from the user
            newBooking = new Booking(bookingID, (Integer) durationField.getSelectedItem(), (String) hotelRoomField.getSelectedItem(), subtotal, roomPrice, nameField.getText(), holidayDate);
            writeToFile();  // calls function to save the new booking to the file
            Window.startMainMenu(); // return to the main menu
        } else if ("return".equals(actionEvent.getActionCommand())) {
            // if the return button has been pressed
            Window.startMainMenu(); // return to the mainmenu
        }
    }

    // the function below is called whenever a combobox value is changed
    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        updatePrice();  // function called to update prices
    }
}
