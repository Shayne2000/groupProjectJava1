package thisFolder;

/**
 *
 * @author korkor
 */
class Booking {
    private String bookingID;
    private String customerID;
    private String tourID;
    
    //Value have different meanings depending on HP/GT
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
    
    //Holiday --> value1 is single room, value2 is double room (2 people)
    //Group --> value1 is total people
    public int getTotalPersons() {
        if (tourID.charAt(0) == 'G') {
            return value1;
        } else if (tourID.charAt(0) == 'H') {
            return value1 + (value2 * 2);
        }
    }

    //Holiday --> value1 is single room
    //Group --> value2 is single room requests
    public int getSingleRooms(){
        if (tourID.charAt(0) == 'H') {
            return value1;
        } else if (tourID.charAt(0) == 'G') {
            return value2;
        }
    }
    
    //Holiday --> value2 is double room
    public int getDoubleRooms(){
        if(tourID.charAt(0) == 'H'){
            return value2;
        } else if (tourID.charAt(0) == 'G') {
            int remaining = getTotalPersons() - getSingleRooms();
            return remaining / 2;
        }
    } 
}
