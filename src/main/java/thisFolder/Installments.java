package thisFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Installments {
    private List<Double> percentages;

    public Installments() {
        this.percentages = new ArrayList<>();
    }

    public void readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        percentages.clear();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 2) {
                try {
                    double pct = Double.parseDouble(parts[1].trim());
                    percentages.add(pct);
                } catch (NumberFormatException e) {
                }
            }
        }
        scanner.close();
    }

    public void printInstallmentInfo(String filePath) {
        System.out.println("Read from " + filePath);
        int totalInstallments = getTotalInstallments();
        System.out.println(totalInstallments + " installments of payment");

        for (int i = 0; i < percentages.size(); i++) {
            System.out.printf("    (%d)  %.1f %% of total\n", (i + 1), percentages.get(i));
        }
        System.out.printf("    (%d)  remaining total\n\n", totalInstallments);
    }

    public int getTotalInstallments() {
        return percentages.size() + 1;
    }

    public List<Double> getPercentages() {
        return percentages;
    }

    public double processAndPrintInstallments(double totalPayment, double currentCashback) {
        int totalInstallments = getTotalInstallments();
        double sumPreviousInstallments = 0.0;

        for (int i = 0; i < percentages.size(); i++) {
            double installmentAmount = totalPayment * (percentages.get(i) / 100.0);
            sumPreviousInstallments += installmentAmount;
            System.out.printf("    installment %d   = %,13.2f\n", (i + 1), installmentAmount);
        }

        double rawLastInstallment = totalPayment - sumPreviousInstallments;
        double usedCashback = Math.min(currentCashback, rawLastInstallment);
        double finalLastInstallment = rawLastInstallment - usedCashback;

        System.out.printf("    installment %d   = %,13.2f  -current cashback (%,10.2f) = %,13.2f\n",
                totalInstallments, rawLastInstallment, currentCashback, finalLastInstallment);

        return usedCashback;
    }
}
