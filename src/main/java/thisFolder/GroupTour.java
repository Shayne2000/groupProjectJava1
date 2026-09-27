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
public class GroupTour extends Tour {
    private final double rate15To20;
    private final double rate21To30;
    private final double rate31Plus;
    private final double singleSupplement;

    public GroupTour(String code, double rate15To20, double rate21To30,
                     double rate31Plus, double singleSupplement) {
        super(code);
        this.rate15To20 = rate15To20;
        this.rate21To30 = rate21To30;
        this.rate31Plus = rate31Plus;
        this.singleSupplement = singleSupplement;
    }
    
    GroupTour(String code) {
        super(code);
        this.rate15To20 = -1;
        this.rate21To30 = -1;
        this.rate31Plus = -1;
        this.singleSupplement = -1;
    }

    @Override
    public double calculatePayment(int persons, int singleRequests) {
        double rate;
        if (persons <= 20) rate = rate15To20;
        else if (persons <= 30) rate = rate21To30;
        else rate = rate31Plus;
        return persons * rate + singleRequests * singleSupplement;
    }
    
    
    public double getRate15To20 () {
        return rate15To20;
    }
    
    public double getRate21To30 () {
        return rate21To30;
    }
    
    public double getRate31Plus () {
        return rate31Plus;
    }
    
    public double getSingleSupplement () {
        return singleSupplement;
    }
}
