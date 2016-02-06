package com.homesoft;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Map;

public class TableTwo {
    private JFrame tableFrame;
    private JPanel bottomPanel;

    private String[] columnNames;
    private Object[][] rowsData;

    public static int nIter;

    public void initTableData( ) {
        columnNames = new String[nIter + 2];
        rowsData    = new String[10][nIter + 2];
        columnNames[0] = "Уровень";
        columnNames[1] = "Кол. не превышающее";

        rowsData[0][0] = "0.3";
        rowsData[1][0] = "0.25";
        rowsData[2][0] = "0.2";
        rowsData[3][0] = "0.18";
        rowsData[4][0] = "0.16";
        rowsData[5][0] = "0.14";
        rowsData[6][0] = "0.12";
        rowsData[7][0] = "0.1";
        rowsData[8][0] = "0.08";
        rowsData[9][0] = "0.06";

        rowsData[0][1] = "-";
        rowsData[1][1] = "-";
        rowsData[2][1] = "-";
        rowsData[3][1] = "-";
        rowsData[4][1] = "-";
        rowsData[5][1] = "-";
        rowsData[6][1] = "-";
        rowsData[7][1] = "-";
        rowsData[8][1] = "-";
        rowsData[9][1] = "-";
        for (int iter = 1; iter <= nIter; iter++) {
            columnNames[iter + 1] = "i=" + String.valueOf(iter);
            rowsData[0][iter + 1] = "-";
            rowsData[1][iter + 1] = "-";
            rowsData[2][iter + 1] = "-";
            rowsData[3][iter + 1] = "-";
            rowsData[4][iter + 1] = "-";
            rowsData[5][iter + 1] = "-";
            rowsData[6][iter + 1] = "-";
            rowsData[7][iter + 1] = "-";
            rowsData[8][iter + 1] = "-";
            rowsData[9][iter + 1] = "-";
        };
    }

    public void buildTable() {
        int wid = 360 + nIter * 55;
        if (wid > 1200) wid = 1200;
        tableFrame = new JFrame("Таблица 2");
        tableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JTable table = new JTable(rowsData, columnNames);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);

        bottomPanel   = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        tableFrame.add(scrollPane, BorderLayout.CENTER);
        tableFrame.add(bottomPanel, BorderLayout.SOUTH);
        tableFrame.pack();
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setSize(wid, 500);
        tableFrame.setVisible(true);

        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(150);

        for (int iter = 1; iter <= nIter; iter++) {
            column = table.getColumnModel().getColumn(iter + 1);
            column.setPreferredWidth(50);
        }
    }

}
