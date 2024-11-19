package com.coffeeisoxigen.ui.drawingpage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.MapGenerator;

public class DrawingPageUI extends JFrame {
    private ControlPanel controlPanel;
    private MapPanel mapPanel;
    private LegendPanel legendPanel;
    private Board board;
    private MapGenerator mapGenerator;

    public DrawingPageUI() {
        setTitle("Map Creator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mapGenerator = new MapGenerator();

        // Initialize panels
        controlPanel = new ControlPanel();
        mapPanel = new MapPanel();
        legendPanel = new LegendPanel();

        // Add panels to frame
        add(controlPanel, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);
        add(legendPanel, BorderLayout.SOUTH);

        // Set button actions
        controlPanel.getGenerateButton().addActionListener(e -> generatePreview());
        controlPanel.getResetButton().addActionListener(e -> resetBoard());
    }

    private void generatePreview() {
        if (board != null) {
            showErrorDialog("Board already created. Please reset to create a new board.");
            return;
        }

        int width, height;
        try {
            width = Integer.parseInt(controlPanel.getWidthField().getText());
            height = Integer.parseInt(controlPanel.getHeightField().getText());
        } catch (NumberFormatException ex) {
            showErrorDialog("Invalid width or height input.");
            return;
        }

        String name = controlPanel.getNameField().getText();
        if (name.isEmpty()) {
            showErrorDialog("Map name cannot be empty.");
            return;
        }

        // Simulate board creation
        mapGenerator.createMap(name, width, height);
        mapPanel.updatePreview(name);
        legendPanel.updateTotalTiles(width * height);
        controlPanel.getGenerateButton().setEnabled(false);
    }

    private void resetBoard() {
        board = null;
        mapPanel.removeAll();
        mapPanel.revalidate();
        mapPanel.repaint();
        legendPanel.updateTotalTiles(0);
        controlPanel.getGenerateButton().setEnabled(true);
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
