package com.coffeeisoxigen.ui.drawingpage;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DrawPageTopPanel extends JPanel {
    private JTextField widthField, heightField, nameField;
    private JButton generateButton, resetButton;

    public DrawPageTopPanel(ActionListener generateAction, ActionListener resetAction) {
        setLayout(new GridLayout(2, 4, 5, 5));

        widthField = new JTextField();
        heightField = new JTextField();
        nameField = new JTextField();

        generateButton = new JButton("Generate Map");
        generateButton.addActionListener(generateAction);

        resetButton = new JButton("Reset Map");
        resetButton.addActionListener(resetAction);

        add(new JLabel("Width:"));
        add(widthField);
        add(new JLabel("Height:"));
        add(heightField);
        add(new JLabel("Map Name:"));
        add(nameField);
        add(generateButton);
        add(resetButton);
    }

    public void setGenerateAction(ActionListener generateAction) {
        generateButton.addActionListener(generateAction);
    }

    public void setResetAction(ActionListener resetAction) {
        resetButton.addActionListener(resetAction);
    }

    public String getWidthText() {
        return widthField.getText();
    }

    public String getHeightText() {
        return heightField.getText();
    }

    public String getNameText() {
        return nameField.getText();
    }

    public void setGenerateButtonEnabled(boolean enabled) {
        generateButton.setEnabled(enabled);
    }
}