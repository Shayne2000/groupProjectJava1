package thisFolder;

import java.util.HashSet;

/**
 *
 * @author korkor
 */
class Booking {
    private String bookingID;
//    private String customerID;
    private String tourID;
    private Tour tourObj;
    private Customer customerObj;
    
    private int totalPeople;
    private int singleRequest;
   
    public Booking(String bookingID, Customer customerObject,
                   String tourID, int value1, int value2) {

        this.bookingID = bookingID;
        this.customerObj = customerObject;
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
    
    public void setTourObject (Tour t) {
        tourObj = t;
    }
    
    public String getBookingID() {return bookingID;}
//    public String getCustomerID() {return customerID;}
    public String getTourID() {return tourID;}
    public int getTotalPeople() {return totalPeople;}
    public int getSingleRequest() {return singleRequest;}
    public int getDoubleRooms() {return (totalPeople-singleRequest)/2;}
    public Tour getTourObject() {return tourObj;}
    public Customer getCustomerObject() {return customerObj;}
    
    public void printBookingData () {
        System.out.printf("Booking %s, customer  %s, current cashback = %.2f\n", bookingID,customerObj.getID(),customerObj.getCashback());
        System.out.printf("             program  %s, %d persons (%d single + %d double rooms)\n", tourID, totalPeople, singleRequest, (totalPeople-singleRequest)/2);
            
            
        double TotalPayment = tourObj.calculatePayment(totalPeople,singleRequest);
        System.out.printf("             total payment     =%,14.2f    future cashback (%,7.2f)\n", TotalPayment, TotalPayment * 0.01);
        Installments.processAndPrintInstallments(TotalPayment,customerObj.getCashback());
        customerObj.setCashback(TotalPayment * 0.01);
        
    }
}

