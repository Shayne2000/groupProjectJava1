/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thisFolder;

import thisFolder.Tour;

/**
 *
 * @author Shayne
 */
public class HolidayPackage extends Tour {
    private final double singleRate;
    private final double doubleRate;

    public HolidayPackage(String code, double singleRate, double doubleRate) {
        super(code);
        this.singleRate = singleRate;
        this.doubleRate = doubleRate;
    }
    
    HolidayPackage(String code) {
        super(code);
        this.singleRate = -1;
        this.doubleRate = -1;
    }

    @Override
    public double calculatePayment(int totalPeople, int singleRequest) {
        return singleRequest * singleRate + (totalPeople-singleRequest) * doubleRate;
    }
    
    public double getSingleRate () {
        return singleRate;
    }
    
    public double getDoubleRate () {
        return doubleRate;
    }
}