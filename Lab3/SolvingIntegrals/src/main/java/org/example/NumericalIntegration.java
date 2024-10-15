package org.example;

public class NumericalIntegration {

    double analitResFunc1 = 1.295836866004329;
    double analitResFunc2 = 1.321958688394446;

    public  double function1(double x) {
        return Math.log(1 + x);
        //return Math.exp(x) * Math.cos(x);
    }

    public double trapezoidMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double res = (function1(a) + function1(b)) / 2;

        for (int i = 1; i < n; i++) {
            res += function1(a + i * h);
        }
        res *= h;
        return res;
    }

    public double simpsonMethod(double a, double b, int n) {
        if (n % 2 != 0) {
            n++;
        }

        double h = (b - a) / n;
        double res = function1(a) + function1(b);

        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                res += 2 * function1(a + i * h);
            }
            else {
                res += 4 * function1(a + i * h);
            }
        }
        res *= (h / 3);
        return res;
    }

    public double calculateOrderTrapezoid(double a, double b, int n) {
        double I_h = trapezoidMethod(a, b, n);
        double I_h0_5 = trapezoidMethod(a, b, n/2);
        double res = Math.abs(Math.log((I_h - analitResFunc1) / (I_h0_5 - analitResFunc1)) / Math.log(2));
        return res;
    }

    public double calculateOrderSimpson(double a, double b, int n) {
        double I_h = simpsonMethod(a, b, n);
        double I_h0_5 = simpsonMethod(a, b, n/2);
        double res = Math.abs(Math.log((I_h - analitResFunc1) / (I_h0_5 - analitResFunc1)) / Math.log(2));
        return res;
    }

    public double rungeTrapezoidError(double a, double b, int n) {
        double I_h = trapezoidMethod(a, b, n);
        double I_h2 = trapezoidMethod(a, b, 2 * n);
        return Math.abs(I_h2 - I_h) / (Math.pow(2, calculateOrderTrapezoid(a, b, n)) - 1);
    }

    public double rungeSimpsonError(double a, double b, int n) {
        double I_h = simpsonMethod(a, b, n);
        double I_h2 = simpsonMethod(a, b, 2 * n);
        return Math.abs(I_h2 - I_h) / (Math.pow(2, calculateOrderSimpson(a, b, n)) - 1);
    }
}
