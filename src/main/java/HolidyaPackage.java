package Project1;

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
