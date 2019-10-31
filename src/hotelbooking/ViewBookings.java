package hotelbooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ViewBookings extends JPanel implements ActionListener {
    // VARIABLE DECLARATION
    private JTextArea outputArea;
    // END OF VARIABLE DECLARATION

    public ViewBookings(){
        initViewBookings();
    }

    private void initViewBookings() {
        // sets the size of the window
        setPreferredSize(new Dimension(500,420));
        setLayout(new FlowLayout(FlowLayout.CENTER));   // sets the layout of the window

        // creates a new button to return to the menu
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(this);   // adds this class as an ActionListener
        returnButton.setPreferredSize(new Dimension(250,80));   // sets the size for the button
        returnButton.setVisible(true);  // sets the buttons visibility to true

        // initialises the text area and sets its size
        outputArea = new JTextArea(20,50);
        outputArea.setEditable(false);      // ensures users cant edit the textarea
        outputArea.setLineWrap(true);       // ensures text wraps for the text area
        outputArea.setWrapStyleWord(true);  // sets the wrap style for the textarea

        // creates a new scroll pane for the textarea
        JScrollPane scroll = new JScrollPane(outputArea);
        // sets the scroll bar to vertical so the user cant scroll side-to-side
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // runs the print bookings function
        printBookings();

        add(scroll);        // adds the scroll area (with the text area inside) ot the JPanel
        add(returnButton);  // adds the return button to the JPanel
    }

    // the funciton below handles file loading, reading and printing
    public void printBookings() {

        // try, catch used incase of exceptions with the file reading
        try {
            // new file object is created accessing the bookings.txt file
            File file = new File("bookings.txt");
            // new buffered reader is created to read the contents of the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;        // String variable declared to hold the current line
            String[] values;    // array of values declared to hold the seperate values on each line

            // output used as title for the text area
            outputArea.setText("=========================BOOKINGS=========================\n");
            // while function runs until there are no more lines in the text file
            while ((line = br.readLine()) != null) {
                outputArea.append("\n");            // starts new line
                values = line.split(",");    // splits the line where a ',' is found and adds the result to the values array

                // outputs all values on seperate lines
                outputArea.append("Booking ID - " + values[0] + "\n");
                outputArea.append("Name - " + values[6] + "\n");
                outputArea.append("Hotel Room - " + values[2] + "\n");
                outputArea.append("Duration - " + values[1] + "\n");
                outputArea.append("Subtotal (inc. VAT) - " + "\n");
                outputArea.append("Start Date - " + values[5] + "\n");
            }
        } catch (Exception e){
            // if exception is found, print error message to console
            System.out.println("Bookings file could not be found");
            System.out.println(e.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // return to menu when a button is pressed
        // no need for an action command as there is only one button on this window
        Window.startMainMenu();
    }
}
