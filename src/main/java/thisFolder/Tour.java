package thisFolder;

import java.util.ArrayList;

public abstract class Tour {
    private final String code;

    protected Tour(String code) { this.code = code; }
    private int totalTravelers = 0;
    private double totalRevenue = 0;
    private final ArrayList<String> bookingsHistory = new ArrayList<>();
    public String getCode() { return code; }
    public abstract double calculatePayment(int firstCount, int secondCount);
    
    @Override
    public boolean equals (Object obj) {
        if (code.equals(((Tour)obj).getCode())){
            return true;
        }
        return false;
    }
    
    public void addTotalTravelers (int n) {
        totalTravelers += n;
    }
    public int getTotalTravelers () {
        return totalTravelers;
    }
    
    public void addTotalRevenue (double n) {
        totalRevenue += n;
    }
    public double getTotalRevenue () {
        return totalRevenue;
    }
    
    public void addHistory (String s){
        bookingsHistory.add(s);
    }
    public String getHistory () {
        return bookingsHistory.toString();
    }
}




