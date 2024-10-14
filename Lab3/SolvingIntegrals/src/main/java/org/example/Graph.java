package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class Graph extends JFrame {

    private NumericalIntegration integration;

    public Graph(String title) {
        super(title);
        this.integration = new NumericalIntegration();
    }

    // Метод для построения графика первообразной функции
    public void plotGraph(double a, double b, int[] nValues) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Построим графики для нескольких значений разбиений (сеток)
        for (int n : nValues) {
            XYSeries series = new XYSeries("n = " + n);

            double h = (b - a) / 100.0;  // количество точек для построения графика
            for (double x = a; x <= b; x += h) {
                double integralValue = integration.simpsonMethod(a, x, n);  // Вычисляем значение интеграла на отрезке
                series.add(x, integralValue);
            }

            dataset.addSeries(series); // Добавляем данные для каждой сетки
        }

        // Создание графика
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph of the Numerical Integral",
                "x", "F(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Настройка графика
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(800, 600));
        setContentPane(panel);
    }


}
