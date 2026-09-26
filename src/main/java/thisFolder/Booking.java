package thisFolder;

/**
 *
 * @author korkor
 */
class Booking {
    private String bookingID;
    private String customerID;
    private String tourID;
    
    private int totalPeople;
    private int singleRequest;
   
    public Booking(String bookingID, String customerID,
                   String tourID, int value1, int value2) {

        this.bookingID = bookingID;
        this.customerID = customerID;
        this.tourID = tourID;
        
        if (tourID.charAt(0) == 'G') {
            // GT: value1 = total people
            //     value2 = single requests
            this.totalPeople = value1;
            this.singleRequest = value2;
        } else {
            // HP: value1 = singles
            //     value2 = doubles (2 people)
            this.totalPeople = value1 + (value2 * 2);
            this.singleRequest = value1;
        }
    }
    
    public String getBookingID() {return bookingID;}
    public String getCustomerID() {return customerID;}
    public String getTourID() {return tourID;}
    public int getTotalPeople() {return totalPeople;}
    public int getSingleRequest() {return singleRequest;}
    
}

