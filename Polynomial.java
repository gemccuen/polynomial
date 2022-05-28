public class Polynomial {
    private final int CO_LENGTH;
    private int inputCount;
    private double[] poly;

    static String num_format(double num) {
        return num < 0 ? " - " + Math.abs(num) : " + " + num;
    }

    public Polynomial() {
        CO_LENGTH = 10;
        inputCount = 0;
        poly = new double[CO_LENGTH];
    }

    public void addCoefficient(double coefficient) {
        if (inputCount < CO_LENGTH) {
            poly[inputCount] = coefficient;
            inputCount++;
        }
    }

    public double[] getPolynomial() {
        return poly;
    }

    public String toString() {
        String polyString = poly[inputCount - 1] + " x^" + (inputCount - 1);
        for (int i = inputCount - 2; i >= 0; i--) {
            if (poly[i] != 0) {
                polyString += num_format(poly[i]) + " x^" + i;
            }
        }
        return polyString;
    }

    public double getValue(double x) {
        double total = 0;
        for (int i = 0; i < inputCount; i++) {
            total += poly[i] * Math.pow(x, i);
        }
        return total;
    }

    public int findRoot(double lower, double upper) {
        double lowerResult = getValue(lower);
        double upperResult = getValue(upper);

        if (lowerResult == 0 && upperResult == 0) {
            return 2;
        } else if (lowerResult == 0) {
            return -1;
        } else if (upperResult == 0) {
            return 1;
        } else if ((lowerResult < 0 && upperResult > 0) || (lowerResult > 0 && upperResult < 0)) {
            return 0;
        } else {
            return -2;
        }
    }
}