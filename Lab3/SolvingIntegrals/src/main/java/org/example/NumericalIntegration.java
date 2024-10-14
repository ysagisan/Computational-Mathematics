package org.example;

public class NumericalIntegration {

    public static double function1(double x) {
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

    public double rungeTrapezoidError(double a, double b, int n, int p) {
        double I_h = trapezoidMethod(a, b, n);
        double I_h2 = trapezoidMethod(a, b, 2 * n);
        return Math.abs(I_h2 - I_h) / (Math.pow(2, p) - 1);
    }

    public double rungeSimpsonError(double a, double b, int n, int p) {
        double I_h = simpsonMethod(a, b, n);
        double I_h2 = simpsonMethod(a, b, 2 * n);
        return Math.abs(I_h2 - I_h) / (Math.pow(2, p) - 1);
    }
}
