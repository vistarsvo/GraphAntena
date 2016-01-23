package com.homesoft;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.*;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class CoolChartDraw {

    private static List<Float> xData = new ArrayList<Float>();
    private static List<Float> yData = new ArrayList<Float>();
    private static List<Float> yNormData = new ArrayList<Float>();
    private JFrame graphFrame;
    private JTabbedPane tabbedPanel;
    private JPanel bottomPanel;
    private int n = 0;

    private Map<Integer, Integer> tabsExists = new HashMap<>();

    public static void setxData(float[] X) {
        xData = new ArrayList<Float>();
        for (int a = 1; a < X.length - 1; a++) {
            xData.add(X[a]);
        }
    }

    public static void setyData(float[] Y) {
        yData = new ArrayList<Float>();
        for (int a = 1; a < Y.length - 1; a++) {
            if (Y[a] > 0) {
                yData.add(Y[a]);
            } else {
                yData.add(1f);
            }
        }
    }

    public static void setyNormData(float[] Y) {
        yNormData = new ArrayList<Float>();
        for (int a = 1; a < Y.length - 1; a++) {
            if (Y[a] > 0) {
                yNormData.add(Y[a]);
            } else {
                yNormData.add(1f);
            }
        }
    }

    public void init() {
        graphFrame = new JFrame("Графики");
        graphFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        tabbedPanel = new JTabbedPane();
         /* BugFix */
        Dimension min  = new Dimension(800,600);
        Dimension max  = new Dimension(Short.MAX_VALUE,Short.MAX_VALUE);
        Dimension pref = new Dimension(800,600); // content gets extra space when large
        JLabel content = new JLabel();
        content.setMinimumSize(min);
        content.setMaximumSize(max);
        content.setPreferredSize(pref);
        tabbedPanel.addTab("0", content);
        bottomPanel   = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        graphFrame.add(tabbedPanel, BorderLayout.CENTER);
        graphFrame.add(bottomPanel, BorderLayout.SOUTH);
        graphFrame.pack();
        graphFrame.setLocationRelativeTo(null);
        tabbedPanel.remove(0);
    }

    public void show() {
        graphFrame.setVisible(true);
    }

    public void addTab(int i, String shortName, String Name) {
        if (tabsExists.containsKey(i)) {
            tabbedPanel.setSelectedIndex(tabsExists.get(i));
        } else {
            tabbedPanel.add(String.valueOf(shortName), createPane(Name));
            tabbedPanel.setSelectedIndex(n);
            tabsExists.put(i,n);
            n++;
        }
    }

    private ChartPanel createPane(String Name) {
        XYSeries series = new XYSeries(Name);
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        for (int i = 0; i < xData.size(); i++) {
            series.add(xData.get(i), yData.get(i));
            //series.add(xData.get(i), yNormData.get(i));
        }

        JFreeChart chart = ChartFactory.createXYLineChart(Name, "Градусы", "Значение", dataset, PlotOrientation.VERTICAL, false, true, false);
        return new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 600);
            }
        };
    }
}

