package com.homesoft;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vistar on 04.02.16.
 */
public class TableOne {
    private JFrame tableFrame;
    private JPanel bottomPanel;

    private String[] columnNames;
    private Object[][] rowsData;

    public static int nIter;

    public void initTableData() {
        columnNames = new String[nIter + 1];
        rowsData    = new String[2][nIter + 1];
        columnNames[0] = "-/-";
        rowsData[0][0] = "-/-";
        rowsData[1][0] = "-/-";
        for (int iter = 1; iter <= nIter; iter++) {
            columnNames[iter] = "Nc=" + String.valueOf(iter);
            rowsData[0][iter] = ".";
            rowsData[1][iter] = ".";
        };
    }

    public void buildTable() {
        tableFrame = new JFrame("Тиблица 1");
        tableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JTable table = new JTable(rowsData, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        bottomPanel   = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tableFrame.add(scrollPane, BorderLayout.CENTER);
        tableFrame.add(bottomPanel, BorderLayout.SOUTH);
        tableFrame.pack();
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setVisible(true);

    }

}
