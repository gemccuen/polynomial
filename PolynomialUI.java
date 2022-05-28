import java.util.Scanner;

public class PolynomialUI {
    private Polynomial polyInstance;
    private double lowerBound;
    private double upperBound;
    private int intervals;
    private final Scanner sc;

    static boolean isDouble(String dString) {
        try {
            Double.parseDouble(dString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public PolynomialUI() {
        polyInstance = new Polynomial();
        lowerBound = 0;
        upperBound = 0;
        intervals = 0;
        sc = new Scanner(System.in);
    }

    public void runPolynomial() {
        boolean done = false;
        
        setPolynomial();
        printPolynomial();
        
        while (!done) {
            setRange();
            printTable();
            System.out.println("\n\nDo you want to redefine your table? (Y/N)");
            sc.nextLine();
            String line = sc.nextLine();
            if (!line.toLowerCase().equals("y")) {
                done = true;
            }
            System.out.println();
        }
    }

    public void setPolynomial() {
        System.out.println("Type in the polynomials in increasing powers:");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.isEmpty()) {
                if (isDouble(line)) {
                    polyInstance.addCoefficient(Double.parseDouble(line));
                } else {
                    System.out.println("Invalid input, please try again.");
                }
            } else {
                break;
            }
        }
    }

    public void printPolynomial() {
        System.out.println("\n" + polyInstance.toString());
    }

    public void setRange() {
        lowerBound = 0;
        upperBound = 0;
        intervals = 0;

        System.out.println("Type in the range:");
        while (lowerBound >= upperBound) {
            System.out.print("Lower Bound (double): ");
            while (!sc.hasNextDouble()) {
                sc.next();
                System.out.println("Invalid input");
                System.out.print("Lower Bound (double): ");
            }

            lowerBound = sc.nextDouble();
            
            System.out.print("Upper Bound (double): ");
            while (!sc.hasNextDouble()) {
                sc.next();
                System.out.println("Invalid input");
                System.out.print("Upper Bound (double): ");
            }

            upperBound = sc.nextDouble();

            if (lowerBound < upperBound) {
                break;
            } else {
                System.out.println("Invalid bounds");
            }
        }

        while (intervals <= 0) {
            System.out.print("Number Of Intervals (int): ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid input");
                System.out.print("Number Of Intervals (int): ");
            }
            intervals = sc.nextInt();

            if (intervals > 0) {
                break;
            }
            System.out.println("Invalid input");
        }
    }

    public void printTable() {
        System.out.printf("\n\n\n%9s %9s %9s\n", "index", "p(index)", "diff(index)");

        double interval = (upperBound - lowerBound) / intervals;
        boolean found = false;

        for (double i = lowerBound; i <= upperBound; i += interval) {
            double p = polyInstance.getValue(i);
            double prev = polyInstance.getValue(i - interval);
            double diff = p - prev;
            System.out.printf("%9.3f %9.3f %9.3f\n", i, p, diff);

            int result = polyInstance.findRoot(i, i + interval);

            switch (result) {
                case -1:
                    if (found) {
                        found = false;
                        break;
                    }
                    System.out.printf("Root found at %.3f\n", i);
                    break;
                case 0:
                    System.out.printf("Root found between %.3f and %.3f\n", i, i + interval);
                    break;
                case 2:
                    System.out.printf("Root found at %.3f and %.3f\n", i, i + interval);
                    found = true;
                    break;
            }
        }
    }
}