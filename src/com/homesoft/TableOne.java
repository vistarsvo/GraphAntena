package com.homesoft;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vistar on 04.02.16.
 */
public class TableOne {
    private JFrame tableFrame;
    private JPanel bottomPanel;

    String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
    Object[][] data = {
            {"Kathy", "Smith",
                    "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
                    "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
                    "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
                    "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
                    "Pool", new Integer(10), new Boolean(false)}
    };
    public void buildTable() {
        tableFrame = new JFrame("Тиблица 1");
        tableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JTable table = new JTable(data, columnNames);

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
