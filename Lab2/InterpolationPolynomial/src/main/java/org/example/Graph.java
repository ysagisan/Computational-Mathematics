package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import static org.example.Interpolation.arccos;

public class Graph {

    public static void plotGraph(double[] nodesX, double[] nodesY, double[] points, double[] nodesX_2, double[] nodesY_2) {
        XYSeries functionSeries = new XYSeries("Исходная функция x^2 + 1 - arccos(x)");
        // XYSeries functionSeries = new XYSeries("Исходная функция |x|");
        XYSeries polynomialSeries = new XYSeries("Полином Лагранжа");
        XYSeries polynomX2 = new XYSeries("Полином c X2 узлами");

        //Первая функция
        for (double x = -1; x <= 1; x += 0.1) {
            functionSeries.add(x, x * x + 1 - arccos(x));
        }

//        //Вторая функция
//        for (double x = -6; x <= 8; x += 0.1) {
//            functionSeries.add(x, Math.abs(x));
//        }

        // Строим интерполяционный полином в точках
        for (double point : points) {
            double interpolatedValue = Interpolation.lagrangeInterpolation(nodesX, nodesY, point);
            polynomialSeries.add(point, interpolatedValue);
        }

        //Строим интерполяционный полином в точках для в два раза больше узлов
        for (double point : points) {
            double interVal = Interpolation.lagrangeInterpolation(nodesX_2, nodesY_2, point);
            polynomX2.add(point, interVal);
        }

        // Создаем набор данных для графика
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(functionSeries);
        dataset.addSeries(polynomialSeries);
        dataset.addSeries(polynomX2);

        // Создаем график
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График интерполяции функции x^2 + 1 - arccos(x)",
                //"График интерполяции функции |x|",
                "x",
                "f(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Отображаем график
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
