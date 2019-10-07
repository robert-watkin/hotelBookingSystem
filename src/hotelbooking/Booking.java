package hotelbooking;

import java.util.Date;

public class Booking {
    // VARIABLE DECLARATION
    private int bookingID;
    private String name;
    private int holidayDuration;
    private Date holidayDate;
    private String hotelRoom;
    private float subtotal;
    private float roomPrice;

    // CONSTRUCTOR
    public Booking(int bookingID, int holidayDuration, String hotelRoom, float subtotal, float roomPrice, String name, Date holidayDate) {
        this.bookingID = bookingID;
        this.holidayDuration = holidayDuration;
        this.hotelRoom = hotelRoom;
        this.subtotal = subtotal;
        this.roomPrice = roomPrice;
        this.name = name;
        this.holidayDate = holidayDate;
    }

    // GETTERS AND SETTERS
    // booking ID
    public int getBookingID() {
        return bookingID;
    }
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    // holiday duration
    public int getHolidayDuration() {
        return holidayDuration;
    }
    public void setHolidayDuration(int holidayDuration) {
        this.holidayDuration = holidayDuration;
    }

    // hotel room
    public String getHotelRoom() {
        return hotelRoom;
    }
    public void setHotelRoom(String hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    // subtotal
    public float getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    // room price
    public float getRoomPrice() {
        return roomPrice;
    }
    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }
    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }
}
