package com.homesoft;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class TableTwo {
    private JFrame tableFrame;
    private JPanel bottomPanel;

    private JButton printButton;
    private JTable table;

    private String[] columnNames;
    private Object[][] rowsData;

    public static int nIter;

    public void initTableData(Map<Integer, Map<Float, Integer>> hMaprLevel) {
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

        Map<Float, Integer> allTotal = new HashMap<>();
        allTotal.put(0.3f, 0);
        allTotal.put(0.25f, 0);
        allTotal.put(0.2f, 0);
        allTotal.put(0.18f, 0);
        allTotal.put(0.16f, 0);
        allTotal.put(0.14f, 0);
        allTotal.put(0.12f, 0);
        allTotal.put(0.1f, 0);
        allTotal.put(0.08f, 0);
        allTotal.put(0.06f, 0);

        for (int iter = 1; iter <= nIter; iter++) {
            Map<Float, Integer> tempMap =  hMaprLevel.getOrDefault(iter, new HashMap<>());
            columnNames[iter + 1] = "i=" + String.valueOf(iter);

            rowsData[0][iter + 1] = String.valueOf(tempMap.getOrDefault(0.3f, -1));
            allTotal.put(0.3f, allTotal.get(0.3f) + tempMap.getOrDefault(0.3f, 0));

            rowsData[1][iter + 1] = String.valueOf(tempMap.getOrDefault(0.25f, -1));
            allTotal.put(0.25f, allTotal.get(0.25f) + tempMap.getOrDefault(0.25f, 0));

            rowsData[2][iter + 1] = String.valueOf(tempMap.getOrDefault(0.2f, -1));
            allTotal.put(0.2f, allTotal.get(0.2f) + tempMap.getOrDefault(0.2f, 0));

            rowsData[3][iter + 1] = String.valueOf(tempMap.getOrDefault(0.18f, -1));
            allTotal.put(0.18f, allTotal.get(0.18f) + tempMap.getOrDefault(0.18f, 0));

            rowsData[4][iter + 1] = String.valueOf(tempMap.getOrDefault(0.16f, -1));
            allTotal.put(0.16f, allTotal.get(0.16f) + tempMap.getOrDefault(0.16f, 0));

            rowsData[5][iter + 1] = String.valueOf(tempMap.getOrDefault(0.14f, -1));
            allTotal.put(0.14f, allTotal.get(0.14f) + tempMap.getOrDefault(0.14f, 0));

            rowsData[6][iter + 1] = String.valueOf(tempMap.getOrDefault(0.12f, -1));
            allTotal.put(0.12f, allTotal.get(0.12f) + tempMap.getOrDefault(0.12f, 0));

            rowsData[7][iter + 1] = String.valueOf(tempMap.getOrDefault(0.1f, -1));
            allTotal.put(0.1f, allTotal.get(0.1f) + tempMap.getOrDefault(0.1f, 0));

            rowsData[8][iter + 1] = String.valueOf(tempMap.getOrDefault(0.08f, -1));
            allTotal.put(0.08f, allTotal.get(0.08f) + tempMap.getOrDefault(0.08f, 0));

            rowsData[9][iter + 1] = String.valueOf(tempMap.getOrDefault(0.06f, -1));
            allTotal.put(0.06f, allTotal.get(0.06f) + tempMap.getOrDefault(0.06f, 0));
        };

        rowsData[0][1] = String.valueOf(allTotal.get(0.3f));
        rowsData[1][1] = String.valueOf(allTotal.get(0.25f));
        rowsData[2][1] = String.valueOf(allTotal.get(0.2f));
        rowsData[3][1] = String.valueOf(allTotal.get(0.18f));
        rowsData[4][1] = String.valueOf(allTotal.get(0.16f));
        rowsData[5][1] = String.valueOf(allTotal.get(0.14f));
        rowsData[6][1] = String.valueOf(allTotal.get(0.12f));
        rowsData[7][1] = String.valueOf(allTotal.get(0.1f));
        rowsData[8][1] = String.valueOf(allTotal.get(0.08f));
        rowsData[9][1] = String.valueOf(allTotal.get(0.06f));
    }

    public void buildTable() {
        int wid = 300 + nIter * 52;
        if (wid > 1200) wid = 1200;
        tableFrame = new JFrame("Таблица 2");
        tableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        table = new JTable(rowsData, columnNames);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);

        bottomPanel   = new JPanel(new FlowLayout(FlowLayout.LEFT));

        printButton = new JButton();
        printButton.setText("Распечатать таблицу");
        bottomPanel.add(printButton);

        tableFrame.add(scrollPane, BorderLayout.CENTER);
        tableFrame.add(bottomPanel, BorderLayout.SOUTH);
        tableFrame.pack();
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setSize(wid, 340);
        tableFrame.setVisible(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        TableColumn column;


        for (int iter = 1; iter <= nIter; iter++) {
            column = table.getColumnModel().getColumn(iter + 1);
            column.setPreferredWidth(50);
            column.setCellRenderer( centerRenderer );
        }

        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment( JLabel.CENTER );

        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(80);
        column.setCellRenderer( centerRenderer2 );
        centerRenderer2.setBackground(new Color(230,230,230));
        centerRenderer2.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer2.setFont(centerRenderer2.getFont().deriveFont(Font.BOLD));

        DefaultTableCellRenderer centerRenderer3 = new DefaultTableCellRenderer();
        centerRenderer3.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer3.setBackground(new Color(240,240,240));
        centerRenderer3.setForeground(new Color(40,40,40));
        centerRenderer3.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer3.setFont(centerRenderer3.getFont().deriveFont(Font.BOLD));

        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(160);
        column.setCellRenderer( centerRenderer3 );




        printButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable.PrintMode printMode = JTable.PrintMode.FIT_WIDTH;
                MessageFormat headerFormat = new MessageFormat("Таблица №2");
                MessageFormat footerFormat = new MessageFormat("--");
                boolean showPrintDialog = true;
                boolean interactive = true;
                try {
                    boolean complete = table.print(printMode, headerFormat, footerFormat, showPrintDialog, null, interactive, null);
                    if (complete) {
                        JOptionPane.showMessageDialog(bottomPanel, "Таблица 2 отправлена на печать");
                    } else {
                        JOptionPane.showMessageDialog(bottomPanel, "Печать таблицы 2 отменена");
                    }
                } catch (PrinterException pe) {
                    JOptionPane.showMessageDialog(bottomPanel, "Возникла ошибка отправки на печать");
                }
            }
        });
    }

}
