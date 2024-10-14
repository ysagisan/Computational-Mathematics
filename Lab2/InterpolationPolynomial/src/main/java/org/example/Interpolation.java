package org.example;

import org.example.Graph;

import java.util.Arrays;
import java.util.Scanner;

public class Interpolation {

    public static double arccos(double x) {
        return Math.acos(x);
    }

    public static double[] generateNodes(double a, double b, int N) {
        double[] nodes = new double[N];
        double step = (b - a) / (N - 1);
        for (int i = 0; i < N; i++) {
            nodes[i] = a + i * step;
        }
        System.out.printf("Узлы: \n");
        for (int i = 0; i < N; i++) {
            System.out.printf("%f  ", nodes[i]);
        }
        System.out.println("\n");
        return nodes;
    }

    public static double[] generateChebyshevNodes(double a, double b, int N) {
        double[] nodes = new double[N];
        for (int i = 0; i < N; i++) {
            double cosValue = Math.cos((2 * i + 1) * Math.PI / (2 * N));
            nodes[i] = 0.5 * (a + b) + 0.5 * (b - a) * cosValue;
        }
        System.out.printf("Узлы: \n");
        for (int i = 0; i < N; i++) {
            System.out.printf("%f  ", nodes[i]);
        }
        System.out.println("\n");
        return nodes;
    }

    public static double searchMaxError(double value, double maxVal) {
        if (value > maxVal) {
            maxVal = value;
        }
        return maxVal;
    }

    public static double lagrangeInterpolation(double[] x, double[] y, double value) {
        int n = x.length;
        double result = 0.0;

        for (int i = 0; i < n; i++) {
            double term = y[i];
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    term = term * (value - x[j]) / (x[i] - x[j]);
                }
            }
            result += term;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод интервала и количества узлов
        System.out.print("Введите начало интервала (a): ");
        double a = scanner.nextDouble();
        System.out.print("Введите конец интервала (b): ");
        double b = scanner.nextDouble();
        System.out.print("Введите количество узлов (N): ");
        int N = scanner.nextInt();
        int N_2 = N * 2;
        double maxInterValue = 0;

        // Генерация узлов интерполяции
        double[] x = generateChebyshevNodes(a, b, N);
        double[] x_2 = generateChebyshevNodes(a, b, N_2);

        double[] y = Arrays.stream(x).map(xi -> xi * xi + 1 - arccos(xi)).toArray();
        double[] y_2 = Arrays.stream(x_2).map(xi -> xi * xi + 1 - arccos(xi)).toArray();

//        double[] y = Arrays.stream(x).map(Math::abs).toArray();
//        double[] y_2 = Arrays.stream(x_2).map(Math::abs).toArray();

        double[] points = generateNodes(a, b, 100);

        System.out.println("Полином Лагранжа в точках:");
        for (double point : points) {
            double interpolatedValue1 = lagrangeInterpolation(x, y, point);
            double interpolatedValue2 = lagrangeInterpolation(x_2, y_2, point);

            double exactValue = point * point + 1 - arccos(point);
            //double exactValue = Math.abs(point);
            System.out.printf("f(%f) = %f | %f (точное значение: %f)\n", point, interpolatedValue1, interpolatedValue2, exactValue);

            double error1 = Math.abs(exactValue - interpolatedValue1);
            double error2 = Math.abs(exactValue - interpolatedValue2);
            System.out.printf("Error: %f | %f\n", error1, error2);
            maxInterValue = searchMaxError(error1, maxInterValue);

        }
        System.out.printf("Самая большая погрешность: %f\n", maxInterValue);
        Graph.plotGraph(x, y, points, x_2, y_2);
    }
}
