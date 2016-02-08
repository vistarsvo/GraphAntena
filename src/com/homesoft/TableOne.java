package com.homesoft;

import javax.print.PrintService;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Map;

public class TableOne {
    private JFrame tableFrame;
    private JPanel bottomPanel;
    private JButton printButton;
    private JTable table;

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
        int wid = 270 + nIter * 55;
        if (wid > 1200) wid = 1200;
        tableFrame = new JFrame("Таблица 1");
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
        tableFrame.setSize(wid, 150);
        tableFrame.setVisible(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        TableColumn column;


        for (int iter = 1; iter <= nIter; iter++) {
            column = table.getColumnModel().getColumn(iter);
            column.setPreferredWidth(50);
            column.setCellRenderer( centerRenderer );
        }

        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(250);

        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer2.setBackground(new Color(230,230,230));
        centerRenderer2.setHorizontalAlignment( JLabel.LEFT );
        centerRenderer2.setFont(centerRenderer2.getFont().deriveFont(Font.BOLD));
        column.setCellRenderer( centerRenderer2 );

        printButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable.PrintMode printMode = JTable.PrintMode.FIT_WIDTH;
                MessageFormat headerFormat = new MessageFormat("Таблица №1");
                MessageFormat footerFormat = new MessageFormat("--");
                boolean showPrintDialog = true;
                boolean interactive = true;

                try {
                        boolean complete = table.print(printMode, headerFormat, footerFormat, showPrintDialog, null, interactive, null);
                        if (complete) {
                            JOptionPane.showMessageDialog(bottomPanel, "Таблица 1 отправлена на печать");
                        } else {
                            JOptionPane.showMessageDialog(bottomPanel, "Печать таблицы 1 отменена");
                        }
                    } catch (PrinterException pe) {
                        JOptionPane.showMessageDialog(bottomPanel, "Возникла ошибка отправки на печать");
                    }
            }
        });

    }

}
