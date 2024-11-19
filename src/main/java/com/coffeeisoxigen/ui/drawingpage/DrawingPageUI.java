package com.coffeeisoxigen.ui.drawingpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.MapGenerator;

public class DrawingPageUI extends JFrame {
    private JTextField widthField, heightField, nameField;
    private JButton generateButton, resetButton;
    private JPanel topPanel, centerPanel, legendPanel;
    private Board board;
    private MapGenerator mapGenerator;

    public DrawingPageUI() {
        setTitle("Map Creator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mapGenerator = new MapGenerator();

        // Top Panel
        topPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        widthField = new JTextField();
        heightField = new JTextField();
        nameField = new JTextField();
        generateButton = new JButton("Generate Map");
        resetButton = new JButton("Reset Map");

        topPanel.add(new JLabel("Width:"));
        topPanel.add(widthField);
        topPanel.add(new JLabel("Height:"));
        topPanel.add(heightField);
        topPanel.add(new JLabel("Map Name:"));
        topPanel.add(nameField);
        topPanel.add(generateButton);
        topPanel.add(resetButton);

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);

        // Legend Panel
        legendPanel = new JPanel();
        legendPanel.setBackground(Color.LIGHT_GRAY);
        legendPanel.setBorder(BorderFactory.createTitledBorder("Legend"));

        // Add panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(legendPanel, BorderLayout.SOUTH);

        // Set button actions
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePreview();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
    }

    private void generatePreview() {
        if (board != null) {
            showErrorDialog("Board already created. Please reset to create a new board.");
            return;
        }

        int width, height;
        try {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
        } catch (NumberFormatException ex) {
            showErrorDialog("Invalid width or height input.");
            return;
        }

        String name = nameField.getText();
        if (name.isEmpty()) {
            showErrorDialog("Map name cannot be empty.");
            return;
        }

        // Use SwingWorker to handle board creation in a background thread
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                mapGenerator.createMap(name, width, height);
                board = mapGenerator.getBoard();
                return null;
            }

            @Override
            protected void done() {
                // Update total tiles in legend panel
                legendPanel.removeAll();
                legendPanel.add(new JLabel("Total Tiles: " + (board.getWidth() * board.getHeight())));
                legendPanel.revalidate();
                legendPanel.repaint();

                // Disable the generate button
                generateButton.setEnabled(false);
            }
        }.execute();
    }

    private void resetBoard() {
        board = null;
        centerPanel.removeAll();
        legendPanel.removeAll();
        legendPanel.add(new JLabel("Total Tiles: 0"));
        legendPanel.revalidate();
        legendPanel.repaint();
        generateButton.setEnabled(true);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingPageUI ui = new DrawingPageUI();
            ui.setVisible(true);
        });
    }
}