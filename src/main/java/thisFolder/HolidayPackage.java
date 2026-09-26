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

    @Override
    public double calculatePayment(int singles, int doubles) {
        return singles * singleRate + 2.0 * doubles * doubleRate;
    }
}