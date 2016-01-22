package com.homesoft;


import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static InputForm inputForm = new InputForm();
    public static ResultsForm resultsForm = new ResultsForm();
    public static Calc calcObject;
    private static Integer iterator = 1;
    private static JFrame resultFrame = new JFrame("Просмотр результатов");
    public static CoolChartDraw coolChartFrame;
    public static ChartDraw chartFrame;

    public static void main(String[] args) {
        calcObject = new Calc();
        showMainFrame();


        resultFrame.setContentPane(resultsForm.MainPanel);
        resultFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        resultFrame.pack();
        resultFrame.setLocationRelativeTo(null);
    }

    private static void showMainFrame() {
        // Show Main Window
        JFrame frame = new JFrame("Диаграмма направленности");
        frame.setContentPane(inputForm.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Add Click Button events
        inputForm.buttonCalc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (setVarsFromFormToCalc() == true) {

                    resultsForm.setMaxSpinner(iterator);
                    resultsForm.resetArrays();

                    for (int i = 1; i <= iterator; i++) {
                        calcObject.setFirstRandom(i); // Randoms
                        calcObject.setSecondRandom(i); //Randoms
                        calcObject.calculateAll(); // Calculate

                        inputForm.progressBar.setValue(i);

                        resultsForm.hMapDG.put(i,calcObject.getDg());
                        resultsForm.hMapRA.put(i,calcObject.getRa());
                        resultsForm.hMapRNA.put(i,calcObject.getRna());
                    }
                    inputForm.progressBar.setValue(0);

                    // Reset All Cool Charts
                    coolChartFrame = new CoolChartDraw();
                    coolChartFrame.init();

                    // Reset All Logariphmic Charts
                    chartFrame = new ChartDraw();
                    chartFrame.init();

                    resultFrame.setVisible(true);
                }
            }
        });
    }

    // Validate after press calc button. Fucking users...
    private static boolean setVarsFromFormToCalc() {
        String N = inputForm.getVarNTextField();
        String Nc = inputForm.getVarNcTextField();
        String KD = inputForm.getVarKDTextField();
        String e = inputForm.getVarETextField();
        String i = inputForm.getVarIteratorTextField();

        if (N.equals("")) { // Check is not empty value
            JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение N не введено!");
            return false;
        } else {
            float fN;
            try {
                fN = Float.parseFloat(N); // Try to convert from string to float
            }
            catch(NumberFormatException errTrace)
            {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение N введено с ошибкой!");
                return false;
            }
            if (fN > 0) {
                calcObject.setN(fN);
            } else {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение N не может быть равно <= 0!");
            }
        }

        if (Nc.equals("")) {
            JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение Nc не введено!");
            return false;
        } else {
            float fNc;
            try {
                fNc = Float.parseFloat(Nc);
            }
            catch(NumberFormatException errTrace)
            {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение Nc введено с ошибкой!");
                return false;
            }
            if (fNc > 0) {
                calcObject.setNc(fNc);
            } else {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение Nc не может быть равно <= 0!");
            }
        }

        if (KD.equals("")) {
            JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение KD не введено!");
            return false;
        } else {
            float fKD;
            try {
                fKD = Float.parseFloat(KD);
            }
            catch(NumberFormatException errTrace)
            {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение KD введено с ошибкой!");
                return false;
            }
            if (fKD > 0) {
                calcObject.setKD(fKD);
            } else {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение KD не может быть равно <= 0!");
            }
        }

        if (e.equals("")) {
            JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение e не введено!");
            return false;
        } else {
            float fe;
            try {
                fe = Float.parseFloat(e);
            }
            catch(NumberFormatException errTrace)
            {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение e введено с ошибкой!");
                return false;
            }
            if (fe > 0) {
                calcObject.setEps(fe);
            } else {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение e не может быть равно <= 0!");
            }
        }

        if (i.equals("")) {
            JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение Итератора не введено!");
            return false;
        } else {
            int fi;
            try {
                fi = Integer.parseInt(i);
            }
            catch(NumberFormatException errTrace)
            {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение Итератора введено с ошибкой!");
                return false;
            }
            if (fi > 0 && fi <= 100) {
                iterator = fi;
                inputForm.progressBar.setMaximum(iterator);
            } else {
                JOptionPane.showMessageDialog(inputForm.MainPanel, "Зачение Итератора может быть от 1 до 100");
            }
        }

        return true;
    }


}
