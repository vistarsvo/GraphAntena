package com.homesoft;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Map;

public class TableOne {
    private JFrame tableFrame;
    private JPanel bottomPanel;

    private String[] columnNames;
    private Object[][] rowsData;

    public static int nIter;

    public void initTableData(Map<Integer, Float> hMapMaxOreol, Map<Integer, Float> hMapFirstLep) {
        columnNames = new String[nIter + 1];
        rowsData    = new String[2][nIter + 1];
        columnNames[0] = "Итерации";
        rowsData[0][0] = "Макс.значение 1го бокового лепестка";
        rowsData[1][0] = "Макс.значение ореола";
        for (int iter = 1; iter <= nIter; iter++) {
            columnNames[iter] = "i=" + String.valueOf(iter);
            rowsData[0][iter] = String.valueOf(Math.round(hMapFirstLep.getOrDefault(iter, -1f) * 1000f) / 1000f);
            rowsData[1][iter] = String.valueOf(Math.round(hMapMaxOreol.getOrDefault(iter, -1f) * 1000f) / 1000f);
        };
    }

    public void buildTable() {
        int wid = 260 + nIter * 55;
        if (wid > 1200) wid = 1200;
        tableFrame = new JFrame("Таблица 1");
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
        tableFrame.setSize(wid, 200);
        tableFrame.setVisible(true);

        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(250);
        for (int iter = 1; iter <= nIter; iter++) {
            column = table.getColumnModel().getColumn(iter);
            column.setPreferredWidth(50);
        }
    }

}
