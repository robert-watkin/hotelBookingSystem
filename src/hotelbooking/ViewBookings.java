package hotelbooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ViewBookings extends JPanel implements ActionListener {

    JTextArea outputArea;
    JButton returnButton;
    JScrollPane scroll;

    public ViewBookings(){
        initViewBookings();
    }

    private void initViewBookings() {
        setSize(500,300);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        JPanel buttonPanel = new JPanel(new GridLayout(0,3,10,10));

        returnButton = new JButton();
        returnButton.setText("Return");
        returnButton.addActionListener(this);
        returnButton.setActionCommand("return");

        buttonPanel.add(new JPanel());
        buttonPanel.add(returnButton);
        buttonPanel.add(new JPanel());
        outputArea = new JTextArea(20,50);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        scroll = new JScrollPane(outputArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        printBookings();

        add(scroll);
        add(returnButton);
    }

    public void printBookings() {

        try {
            File file = new File("bookings.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String[] values;
            outputArea.setText("=========================BOOKINGS=========================\n");
            while ((line = br.readLine()) != null) {
                outputArea.append("\n");
                values = line.split(",");

                outputArea.append("Booking ID - " + values[0] + "\n");
                outputArea.append("Name - " + values[6] + "\n");
                outputArea.append("Hotel Room - " + values[2] + "\n");
                outputArea.append("Duration - " + values[1] + "\n");
                outputArea.append("Subtotal (inc. VAT) - " + "\n");
                outputArea.append("Start Date - " + values[5] + "\n");
            }
        } catch (Exception e){
            System.out.println("Bookings file could not be found");
            System.out.println(e.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("return".equals(actionEvent.getActionCommand())){
            Window.startMainMenu();
        }
    }
}
