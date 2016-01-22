package com.homesoft;

import org.knowm.xchart.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartDraw {
    private static List<Float> xData = new ArrayList<Float>();
    private static List<Float> yData = new ArrayList<Float>();
    private JFrame graphFrame;
    private JTabbedPane tabbedPanel;
    private JPanel bottomPanel;
    private int n = 0;

    private static float MinY = 0f;
    private static float MaxY = 0f;

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
            if (MinY == 0) MinY = Y[a];
            if (MinY > Y[a]) MinY = Y[a];
            if (MaxY < Y[a]) MaxY = Y[a];

            if (Y[a] > 0) {
                yData.add(Y[a]);
            } else {
                yData.add(1f);
            }
        }
    }

    public void init() {
        graphFrame = new JFrame("Графики");
        graphFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        tabbedPanel = new JTabbedPane();
         /* BugFix */
        Dimension min = new Dimension(800, 600);
        Dimension max = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
        Dimension pref = new Dimension(800, 600); // content gets extra space when large
        JLabel content = new JLabel();
        content.setMinimumSize(min);
        content.setMaximumSize(max);
        content.setPreferredSize(pref);
        tabbedPanel.addTab("0", content);
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
            tabsExists.put(i, n);
            n++;
        }
    }

    public static XChartPanel createPane(String Name) {
        Chart chart = new ChartBuilder().width(800).height(400).build();

        chart.getStyleManager().setChartTitleVisible(false);
        //chart.getStyleManager().setLegendVisible(false);
        chart.getStyleManager().setLegendPosition(StyleManager.LegendPosition.InsideNW);
        chart.getStyleManager().setYAxisLogarithmic(true);
        chart.getStyleManager().setXAxisLabelRotation(90);

        chart.setXAxisTitle("Градусы");
        chart.getStyleManager().setXAxisMax(181);
        chart.getStyleManager().setXAxisMin(0);
        // Y scale
        chart.getStyleManager().setYAxisMax(MaxY + Math.round(MaxY * 0.5));
        chart.getStyleManager().setYAxisMin(MinY / 3);
        // Series
        chart.addSeries(Name, xData, yData);
        return new XChartPanel(chart);
    }
}
