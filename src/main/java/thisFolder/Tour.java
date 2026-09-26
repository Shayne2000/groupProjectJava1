package thisFolder;

public abstract class Tour {
    private final String code;

    protected Tour(String code) { this.code = code; }
    public String getCode() { return code; }
    public abstract double calculatePayment(int firstCount, int secondCount);
}




