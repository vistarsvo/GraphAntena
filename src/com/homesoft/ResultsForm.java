package com.homesoft;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ResultsForm {
    public JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel ComponentsPanel;
    private JPanel BottomPanel;
    private JButton choseButton;
    private JSpinner spinner1;
    private JPanel ChoserPanel;
    private JPanel DGPanel;
    private JPanel RAPanel;
    private JPanel RNAPanel;
    private JTextPane DGText;
    private JTextPane RNAText;
    private JTextPane RAText;
    private JButton BuildLogGraph;
    private JButton CoolChartButton;
    private JButton NormGraphButton;
    private JButton showTable1;
    private JButton showTable2;

    private static JFrame table1Frame = new JFrame("Просмотр таблицы 1");
    private static JFrame table2Frame = new JFrame("Просмотр таблицы 2");
    private static TableOne table1 = new TableOne();

    private int maxSpinner = 100;
    private float[] Dg;
    private float[] Ra;
    private float[] Rna;

    Map<Integer, float[]> hMapDG  = new HashMap<>();
    Map<Integer, float[]> hMapRA  = new HashMap<>();
    Map<Integer, float[]> hMapRNA = new HashMap<>();

    // FORM Constructor. Called automatic
    public ResultsForm() {
        spinner1.setValue(1);

        // Spinner validation
        spinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if ((int)spinner1.getValue() < 1) spinner1.setValue(1);
                if ((int)spinner1.getValue() > maxSpinner) spinner1.setValue(maxSpinner);
            }
        });

        // Show results button click listener
        choseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showResults();
            }
        });
        BuildLogGraph.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (validateSpinner()) {
                    int i = (int) spinner1.getValue();
                    if (hMapDG.containsKey(i)) {
                        Dg = hMapDG.get(i);
                        Ra = hMapRA.get(i);
                        Rna = hMapRNA.get(i);

                        Main.chartFrame.setxData(Dg);
                        Main.chartFrame.setyData(Ra);
                        Main.chartFrame.setyNormData(Rna);

                        Main.chartFrame.addTab(i, String.valueOf(i), "Логорифмический график по итерации №" + i);
                        Main.chartFrame.show();
                    }
                }
            }
        });

        CoolChartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (validateSpinner()) {
                    int i = (int) spinner1.getValue();
                    if (hMapDG.containsKey(i)) {
                        Dg = hMapDG.get(i);
                        Ra = hMapRA.get(i);
                        Rna = hMapRNA.get(i);

                        Main.coolChartFrame.setxData(Dg);
                        Main.coolChartFrame.setyData(Ra);
                        Main.coolChartFrame.setyNormData(Rna);

                        Main.coolChartFrame.addTab(i, String.valueOf(i), "График по итерации №" + i);
                        Main.coolChartFrame.show();
                    }
                }
            }
        });
        NormGraphButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (validateSpinner()) {
                    int i = (int) spinner1.getValue();
                    if (hMapDG.containsKey(i)) {
                        Dg = hMapDG.get(i);
                        Ra = hMapRA.get(i);
                        Rna = hMapRNA.get(i);

                        Main.coolChartFrame.setxData(Dg);
                        Main.coolChartFrame.setyData(Rna);
                        Main.coolChartFrame.setyNormData(Rna);

                        Main.coolChartFrame.addTab(0 - i, String.valueOf(i), "График по итерации (нормированный)№" + i);
                        Main.coolChartFrame.show();
                    }
                }
            }
        });

        showTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                table1.initTableData();
                table1.buildTable();
            }
        });
    }


    public void setMaxSpinner(int maxSpinner) {
        this.maxSpinner = maxSpinner;
    }

    public void resetArrays() {
        hMapDG.clear();
        hMapRA.clear();
        hMapRNA.clear();
        DGText.setText("[ Для Просмотра результатов в виде значений нужно выбрать итерацию и нажать \"Посмотреть\" ]");
        RAText.setText("[ Для Просмотра результатов в виде значений нужно выбрать итерацию и нажать \"Посмотреть\" ]");
        RNAText.setText("[ Для Просмотра результатов в виде значений нужно выбрать итерацию и нажать \"Посмотреть\" ]");
    }

    private void showResults() {
        if (validateSpinner()) {
            int i = (int) spinner1.getValue();
            if (hMapDG.containsKey(i)) {
                String tempDG  = "Итерация № " + i + ":\n";
                String tempRa  = "Итерация № " + i + ":\n";
                String tempRna = "Итерация № " + i + ":\n";
                Dg  = hMapDG.get(i);
                Ra  = hMapRA.get(i);
                Rna = hMapRNA.get(i);
                for (int arrayPos = 1; arrayPos < Dg.length; arrayPos++) {
                    tempDG  += (int)Dg[arrayPos] + "; ";
                    tempRa  += (Math.round(Ra[arrayPos] * 1000f) / 1000f) + "; ";
                    tempRna += (Math.round(Rna[arrayPos] * 100f) / 100f) + "; ";
                }
                DGText.setText(tempDG);
                RAText.setText(tempRa);
                RNAText.setText(tempRna);
            }
        }
    }

    private boolean validateSpinner() {
        if ((int) spinner1.getValue() < 1) {
            spinner1.setValue(1);
            JOptionPane.showMessageDialog(MainPanel, "Значение итериции не может быть менее 1. Значение сброшено.");
            return false;
        }
        if ((int) spinner1.getValue() > maxSpinner) {
            spinner1.setValue(maxSpinner);
            JOptionPane.showMessageDialog(MainPanel, "Значение итериции не может быть более установленного. Значение сброшено на максимальное.");
            return false;
        }
        return true;
    }
}
