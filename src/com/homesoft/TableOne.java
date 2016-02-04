package com.homesoft;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vistar on 04.02.16.
 */
public class TableOne {

    public JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JPanel ComponentsPanel;
    private JLabel MainLable;

    //Массив содержащий заголоки таблицы
    private Object[] headers = { "Name", "Surname", "Telephone" };

    //Массив содержащий информацию для таблицы
    private Object[][] data = {
            { "John", "Smith", "1112221" },
            { "Ivan", "Black", "2221111" },
    };

    //Объект таблицы
    private JTable jTabPeople;

    public void buildTable() {
        //Создаем новую таблицу на основе двумерного массива данных и заголовков
        jTabPeople = new JTable(data, headers);
        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
        JScrollPane jscrlp = new JScrollPane(jTabPeople);
        //Устаналиваем размеры прокручиваемой области
        jTabPeople.setPreferredScrollableViewportSize(new Dimension(250, 100));
        //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
        ComponentsPanel.add(jscrlp);
    }

}
