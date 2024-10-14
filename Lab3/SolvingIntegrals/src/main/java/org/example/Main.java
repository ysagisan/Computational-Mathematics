package org.example;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        NumericalIntegration res = new NumericalIntegration();

        double analitResFunc1 = 1.295836866004329;
        double analitResFunc2 = 1.321958688394446;

        double finalResTrap = res.trapezoidMethod(0, 2, 10);
        double finalResSimp = res.simpsonMethod(0,2,10);

        double err1 = res.rungeTrapezoidError(0, 2, 10, 4);
        double err2 = res.rungeSimpsonError(0, 2, 10, 4);

        double errTrap = Math.abs(finalResTrap - analitResFunc1);
        double errSimp = Math.abs(finalResSimp - analitResFunc1);

        System.out.printf("Метод трапеций: %f\n", finalResTrap);
        System.out.printf("Метода Симпсона: %f\n", finalResSimp);
        System.out.printf("Отличие с аналитическим решением метод трапеций: %f\n", errTrap);
        System.out.printf("Отличие с аналитическим решением метод Симпсона: %f\n", errSimp);
        System.out.printf("Ошибка Рунге метод трапеций: %f\n", err1);
        System.out.printf("Ошибка Рунге метод Симпсона: %f\n", err2);

        SwingUtilities.invokeLater(() -> {
            Graph example = new Graph("Numerical Integration Graph");
            example.setSize(800, 600);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // Построение графика с разными значениями n (разбиений)
            int[] nValues = {2, 6, 10}; // Массив значений для количества разбиений
            example.plotGraph(0, 2, nValues);  // Строим график на отрезке [0, 2]
            example.setVisible(true);
        });
    }
}
