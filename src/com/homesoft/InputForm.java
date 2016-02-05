package com.homesoft;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InputForm {
    public JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel ComponentsPanel;
    private JPanel BottomPanel;
    public JProgressBar progressBar;
    private JTextField varNTextField;
    private JTextField varNcTextField;
    private JTextField varKDTextField;
    private JTextField varETextField;
    public JButton buttonCalc;

    private JLabel copyRightLable;
    private JLabel varNLable;
    private JLabel MainLable;
    private JLabel varNcLable;
    private JLabel varKdLable;
    private JLabel varELable;
    private JTextField varIterator;
    private JLabel iteratorLabel;
    private JTextField varDFMaxTextField;
    private JLabel m;
    private JTextField varMTextField;
    private JTextField varDASigma;


    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public String  getVarNTextField() {
        return varNTextField.getText();
    }

    public String getVarNcTextField() {
        return varNcTextField.getText();
    }

    public String getVarKDTextField() {
        return varKDTextField.getText();
    }

    public String getVarETextField() {
        return varETextField.getText();
    }

    public String getVarIteratorTextField() {
        return varIterator.getText();
    }
    public String getvarDFMaxTextField() {
        return varDFMaxTextField.getText();
    }

    public String getvarMTextField() {
        return varMTextField.getText();
    }

    public String getVarDASigma() {
        return varDASigma.getText();
    }

    public InputForm() {
        // Validator

        varDASigma.getDocument().addDocumentListener(new DocumentListener() {
            float tempDAs;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                try { tempDAs = Float.parseFloat(varDASigma.getText()); }
                catch(NumberFormatException e) { tempDAs = -1; }

                if ( tempDAs < 0) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Значение не может быть от 0 до 1000.");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                try { tempDAs = Float.parseFloat(varDASigma.getText()); }
                catch(NumberFormatException e) { tempDAs = -1; }
                if (tempDAs < 0 && !varDASigma.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Значение не может быть от 0 до 1000.");
                }
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

        varMTextField.getDocument().addDocumentListener(new DocumentListener() {
            float tempM;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                try { tempM = Float.parseFloat(varMTextField.getText()); }
                catch(NumberFormatException e) { tempM = -1; }

                if ( tempM < 0) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Значение не может быть от 0 до 1000. Разделитель точка");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                try { tempM = Float.parseFloat(varMTextField.getText()); }
                catch(NumberFormatException e) { tempM = -1; }
                if (tempM < 0 && !varMTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Значение не может быть от 0 до 1000. Разделитель точка");
                }
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

        varDFMaxTextField.getDocument().addDocumentListener(new DocumentListener() {
            float tempF;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varDFMaxTextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }

                if ( tempF < 0) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Значение не может быть от 0 до 360.");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varDFMaxTextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }
                if (tempF < 0 && !varDFMaxTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Значение не может быть от 0 до 360.");
                }
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

        // Validator
        varNTextField.getDocument().addDocumentListener(new DocumentListener() {
            float tempF;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varNTextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }

                if ( tempF <= 0) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Разделитель дробной части 'точка'. Значение не может быть <= 0.");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varNTextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }
                if (tempF <= 0 && !varNTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Разделитель дробной части 'точка'. Значение не может быть <= 0.");
                }
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

        varNcTextField.getDocument().addDocumentListener(new DocumentListener() {
            float tempF;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varNcTextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }

                if ( tempF <= 0) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Разделитель дробной части 'точка'. Значение не может быть <= 0.");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varNcTextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }
                if (tempF <= 0 && !varNcTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Разделитель дробной части 'точка'. Значение не может быть <= 0.");
                }
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

        varKDTextField.getDocument().addDocumentListener(new DocumentListener() {
            float tempF;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varKDTextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }

                if ( tempF <= 0) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Разделитель дробной части 'точка'. Значение не может быть <= 0.");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varKDTextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }
                if (tempF <= 0 && !varKDTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Разделитель дробной части 'точка'. Значение не может быть <= 0.");
                }
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

        varETextField.getDocument().addDocumentListener(new DocumentListener() {
            float tempF;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varETextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }

                if ( tempF <= 0) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Разделитель дробной части 'точка'. Значение не может быть <= 0.");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varETextField.getText()); }
                catch(NumberFormatException e) { tempF = -1; }
                if (tempF <= 0 && !varETextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число. Разделитель дробной части 'точка'. Значение не может быть <= 0.");
                }
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

        varIterator.getDocument().addDocumentListener(new DocumentListener() {
            float tempF;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varIterator.getText()); }
                catch(NumberFormatException e) { tempF = -1; }
                if ( tempF <= 0) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число от 1 до 100");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                try { tempF = Float.parseFloat(varIterator.getText()); }
                catch(NumberFormatException e) { tempF = -1; }
                if ((tempF <= 0 || tempF > 100) && !varIterator.getText().equals("")) {
                    JOptionPane.showMessageDialog(MainPanel, "Поле должно содержать число от 1 до 100.");
                }
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });



    }

    private void createUIComponents() {

    }
}
