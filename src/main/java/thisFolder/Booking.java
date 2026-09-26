package thisFolder;

import java.util.*;
import java.io.*;
/**
 *
 * @author korkor
 */
abstract class Booking {
    private String bookingID;
    private String customerID;
    private String tourID;
    //value will be different depends on HP/GT
    private int value1;
    private int value2;
   
    public Booking(String bookingID, String customerID,
                   String tourID, int value1, int value2) {

        this.bookingID = bookingID;
        this.customerID = customerID;
        this.tourID = tourID;
        this.value1 = value1;
        this.value2 = value2;
    }
    
    public String getBookingID() {return bookingID;}
    public String getCustomerID() {return customerID;}
    public String getTourID() {return tourID;}
    public int getValue1() {return value1;}
    public int getValue2() {return value2;}
    
    public abstract int getTotalPersons();
    public abstract int getSingleRooms();
    public abstract int getDoubleRooms();

}

class HolidayBooking extends Booking {
    public HolidayBooking (String bookingID, String customerID,
                          String tourID, int value1, int value2) {
        super(bookingID, customerID, tourID, value1, value2);
        
    }
    
    @Override
    //value1 is single room, value2 is double room (2 people)
    public int getTotalPersons() {
        return getValue1() + (getValue2() * 2);
    }
    
    @Override
    public int getSingleRooms(){
        return getValue1();
    }
    
    @Override
    public int getDoubleRooms(){
        return getValue2();
    }
}

class GroupBooking extends Booking {

    public GroupBooking(String bookingID, String customerID, String tourID
                        , int value1, int value2) {
        super(bookingID, customerID, tourID, value1, value2);
    }

    @Override
    //value1 is persons
    public int getTotalPersons() {
        return getValue1();
    }

    @Override
    //value2 is single requests
    public int getSingleRooms() {
        return getValue2();
    }

    @Override
    public int getDoubleRooms() {
        int remaining = getTotalPersons() - getSingleRooms();
        return remaining / 2;
    }
}

