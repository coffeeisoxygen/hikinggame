package com.coffeeisoxigen.ui.drawingpage;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {
    private JTextField widthField, heightField, nameField;
    private JButton generateButton, resetButton;

    public ControlPanel() {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        widthField = new JTextField();
        heightField = new JTextField();
        nameField = new JTextField();
        generateButton = new JButton("Generate Map");
        resetButton = new JButton("Reset Map");

        add(createLabeledField("Width:", widthField));
        add(createLabeledField("Height:", heightField));
        add(createLabeledField("Map Name:", nameField));
        add(generateButton);
        add(resetButton);
    }

    private JPanel createLabeledField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(labelText), BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    public JTextField getWidthField() {
        return widthField;
    }

    public JTextField getHeightField() {
        return heightField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }
}
