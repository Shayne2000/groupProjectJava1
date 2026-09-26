/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package thisFolder;
//import thisFolder.Tour;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Shayne
 */

class helper {
    public static String cleanArray (String array[]){
        return "";
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
                GTs.add(new GroupTour(values[0],values[1],values[2],values[3],values[4]));
            }else if(line.charAt(0) == 'H'){
                HPs.add(new HolidayPackage(values[0],values[1],values[2]));
            }else{
                System.out.println("cannot catagorize this ID");
            }
        }
        
        System.out.println("Group Tours: price per person");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Code   15-20 persons   21-30 persons   >=31 persons   Single Supplement");
        System.out.println("-----------------------------------------------------------------------");
        for (GroupTour GT : GTs) {
            System.out.printf("%s%16d%16d%16d%16d\n", GT.getCode());
        }
        
        System.out.println();
        System.out.println("Holiday Packages: price per person");
        System.out.println("-------------------------------------------");
        System.out.println("Code   1 person(Single)   2 persons(Double)");
        System.out.println("-------------------------------------------");
        for (HolidayPackage HP : HPs) {
            System.out.printf("%s%16d%18d");
        }
        
        System.out.println();
        Scanner installmentScanner = readFile("installments.txt");
        System.out.printf("%dinstallments of payment\n");
        for (int i = 0 ; i < 0 ; i++) {
            System.out.printf("  (%d)  %2.1f % of total\n", args);
        }
        System.out.printf("  (%d)  remaining total\n");
        
        System.out.println();
        Scanner bookScanner = readFile("bookings.txt");
        System.out.println();
        System.out.println("===== Booking Processing =====");
        
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();
        while (bookScanner.hasNextLine()) {
            String line = bookScanner.nextLine();
            String value[] = line.split(",");
            
            Customer customer = new Customer(value[1]);
            int targetIndex = customers.indexOf(customer);
            if (targetIndex == -1) {
                customers.add(customer);
            }else{
                customer = customers.get(targetIndex);
            }
            
            Booking booking = new Booking(value[0],value[1],value[2],value[3],value[4]);
            bookings.add(booking);
            System.out.printf("Booking  %s, customer  %s, current cashback = %.2f\n", value[0],value[1],customer.getCashback());
            System.out.printf("             program  %s, %d persons (%d single + %d double rooms)\n", value[2], booking.getSingleRooms(),booking.getDoubleRooms());
            System.out.printf("installment functions\n");
            System.out.println();
        }
        
        System.out.println("===== Group-Tour Sumary =====");
        for (GroupTour GT : GTs){
            System.out.printf("%s     total travelers =%5d    total revenur =%10.2f    bookings = %s", GT.getCode(), );
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
