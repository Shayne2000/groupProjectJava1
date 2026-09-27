/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package thisFolder;
//import thisFolder.Tour;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Shayne
 */

class helper {
    public static String[] cleanArray (String array[],int start,int size){
        try {
            for (int i = start ; i < size ; i ++){
                array[i] = array[i].trim();
            }
        }catch(ArrayIndexOutOfBoundsException e){
            
        }
            
        return array;
    }
    public static int INT (String s) {
        return Integer.parseInt(s);
        
    }
}

class InvalidInputException extends RuntimeException {
    
    public InvalidInputException (int target, int column) {
        String dummy = "For amount in col" + column + ": \"" + target + "\"";
        super(dummy);
    }
    
    public InvalidInputException (String input) {
        String dummy = "For tour code: \"" + input + "\"";
        super(dummy);
    }
}



public class main {
    
    private static final String inputFolder = "src/main/java/inputs/";
    private static final Scanner userInput = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        ArrayList<GroupTour> GTs = new ArrayList<>();
        ArrayList<HolidayPackage> HPs = new ArrayList<>();
        Scanner tourScanner = readFile("tours.txt");
        
        while (tourScanner.hasNext()){
            String line = tourScanner.nextLine();
            String values[] = line.split(",");
            if (line.charAt(0) == 'G'){
                values = helper.cleanArray(values, 0, 5);
                GTs.add(new GroupTour(values[0],helper.INT(values[1]),helper.INT(values[2]),helper.INT(values[3]),helper.INT(values[4])));
            }else if(line.charAt(0) == 'H'){
                values = helper.cleanArray(values, 0, 3);
                HPs.add(new HolidayPackage(values[0],helper.INT(values[1]),helper.INT(values[2])));
            }else{
                System.out.println("cannot catagorize this ID");
            }
        }
        tourScanner.close();
        
        System.out.println("Group Tours: price per person");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Code   15-20 persons   21-30 persons   >=31 persons   Single Supplement");
        System.out.println("-----------------------------------------------------------------------");
        for (GroupTour GT : GTs) {
            System.out.printf("%s%,16.0f%,16.0f%,15.0f%,16.0f\n",GT.getCode(), GT.getRate15To20(),GT.getRate21To30(),GT.getRate31Plus(),GT.getSingleSupplement());
        }
        
        System.out.println();
        System.out.println("Holiday Packages: price per person");
        System.out.println("-------------------------------------------");
        System.out.println("Code   1 person(Single)   2 persons(Double)");
        System.out.println("-------------------------------------------");
        for (HolidayPackage HP : HPs) {
            System.out.printf("%s%,16.0f%,18.0f\n", HP.getCode(), HP.getSingleRate(), HP.getDoubleRate());
        }
        
        System.out.println();
        Scanner installmentScanner = readFile("installments.txt");
        
        while (installmentScanner.hasNextLine()) {
            String line = installmentScanner.nextLine();

            String[] parts = line.split(",");
            parts = helper.cleanArray(parts, 0, 2);
            
            double pct = Double.parseDouble(parts[1]);
            Installments.addPercentages(pct);
            
        }
        installmentScanner.close();
        
        System.out.printf("%d installments of payment\n",Installments.getTotalInstallments());
        for (int i = 0 ; i < Installments.getTotalInstallments() -1 ; i++) {
            System.out.printf("  (%d)  %2.1f %% of total\n", i + 1 , Installments.getPercentages(i));
        }
        System.out.printf("  (%d)  remaining total\n",Installments.getTotalInstallments());
        
        
        
        System.out.println();
        Scanner bookScanner = readFile("bookings_errors.txt");
//        System.out.println();
        
        
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();
        while (bookScanner.hasNextLine()) {
            String line = bookScanner.nextLine();
            String value[] = line.split(",");
            
            value = helper.cleanArray(value, 0, 5);
            
            Tour tour = new GroupTour(value[2]);
            int tourIndex = GTs.indexOf(tour);
            try {
                if (tourIndex == -1) {
                    tour = new HolidayPackage(value[2]);
                    tourIndex = HPs.indexOf(tour);
                    if (tourIndex == -1) {
                        throw new InvalidInputException(value[2]);
                    }
                    tour = HPs.get(tourIndex);
                }else{
                    tour = GTs.get(tourIndex);
                }
            }catch(InvalidInputException e){
                System.out.printf("%s\n",e);
                System.out.printf("%-34s--> skip this booking\n\n", line);
                continue;
            }
            
            Customer customer = new Customer(value[1]);
            int targetIndex = customers.indexOf(customer);
            if (targetIndex == -1) {
                customers.add(customer);
            }else{
                customer = customers.get(targetIndex);
            }
            try {
                if (helper.INT(value[3]) < 0) {
                    throw new InvalidInputException(helper.INT(value[3]),4);
                }else if(helper.INT(value[4]) < 0){
                    throw new InvalidInputException(helper.INT(value[4]),5);
                }
            }catch(InvalidInputException e) {
                System.out.printf("%s\n", e);
                System.out.printf("%-34s--> skip this booking\n\n", line);
                continue;
            }catch(NumberFormatException e){
                System.out.printf("%s\n", e);
                System.out.printf("%-34s--> skip this booking\n\n", line);
                continue;
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.printf("%s\n", e);
                System.out.printf("%-34s--> skip this booking\n\n", line);
                continue;
            }
            Booking booking = new Booking(value[0],customer,value[2],helper.INT(value[3]),helper.INT(value[4]));
            
            
            
//            System.out.printf("%s\n",GTs.toString());
            
            bookings.add(booking);
            booking.setTourObject(tour);
            tour.addHistory(value[0]);
            tour.addTotalRevenue(tour.calculatePayment(booking.getTotalPeople(), booking.getSingleRequest()));
            tour.addTotalTravelers(booking.getTotalPeople());
            
//            System.out.println();
        }
        bookScanner.close();
        
        System.out.println("===== Booking Processing =====");
        for (Booking booking : bookings) {
            booking.printBookingData();
        }
        
        
        System.out.println("\n===== Group-Tour Sumary =====");
        for (GroupTour GT : GTs){
            System.out.printf("%s     total travelers =%5d    total revenue =%10.2f    bookings = %s\n", GT.getCode(),GT.getTotalTravelers(),GT.getTotalRevenue(),GT.getHistory() );
        }
        
        System.out.println("\n===== Holiday-Package Sumary =====");
        for (HolidayPackage HP : HPs){
            System.out.printf("%s     total travelers =%5d    total revenue =%10.2f    bookings = %s\n", HP.getCode(),HP.getTotalTravelers(),HP.getTotalRevenue(),HP.getHistory() );
        }
    }
    
    private static Scanner readFile(String expectedName){
        
        Scanner scanObject ;
        
        while (true) {
            try {
                scanObject = new Scanner(Path.of(inputFolder+expectedName));
                break;
            }catch(IOException e) {
                System.out.printf("error : %s\n", e);
                System.out.println("Enter correct file name =");
                expectedName = userInput.nextLine();
                System.out.println();
            }
//            System.out.println("test");
        }
        System.out.printf("Read from %s\n", inputFolder+expectedName);
        
        while (scanObject.hasNextLine()){
            if (scanObject.hasNext("(?m)^#.*")){//Pattern.compile("^\\s*#.*")
//                System.out.printf("kill # attemp : ");
//                System.out.printf("%s\n", scanObject.nextLine());
                scanObject.nextLine();
            }else{
                break;
            }
        }
        
        
        return scanObject;
    }
}
