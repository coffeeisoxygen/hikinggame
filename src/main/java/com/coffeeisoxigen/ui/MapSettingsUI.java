package com.coffeeisoxigen.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.coffeeisoxigen.model.board.ETileType;

public class MapSettingsUI extends JFrame {
    private JTextField widthField;
    private JTextField heightField;
    private JPanel previewPanel;
    private Map<ETileType, Color> tileColors;
    private JComboBox<ETileType> tileTypeDropdown;
    private JButton colorPickerButton;

    public MapSettingsUI() {
        setTitle("Map Settings");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout());

        tileColors = new HashMap<>();
        initializeDefaultTileColors();
        initializeUI();
    }

    private void initializeUI() {
        // Panel kiri untuk input dan legend
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setPreferredSize(new Dimension(300, 600));

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel widthLabel = new JLabel("Width:");
        widthField = new JTextField();
        JLabel heightLabel = new JLabel("Height:");
        heightField = new JTextField();

        JButton previewButton = new JButton("Preview");
        previewButton.addActionListener(this::generatePreview);

        inputPanel.add(widthLabel);
        inputPanel.add(widthField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(new JLabel());
        inputPanel.add(previewButton);

        // Legend Panel
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBorder(BorderFactory.createTitledBorder("Legend Map"));

        tileTypeDropdown = new JComboBox<>(ETileType.values());
        colorPickerButton = new JButton("Pick Color");
        colorPickerButton.addActionListener(e -> {
            ETileType selectedType = (ETileType) tileTypeDropdown.getSelectedItem();
            Color selectedColor = JColorChooser.showDialog(this, "Pick a Color", tileColors.get(selectedType));
            if (selectedColor != null) {
                tileColors.put(selectedType, selectedColor);
                colorPickerButton.setBackground(selectedColor);
            }
        });

        legendPanel.add(new JLabel("Tile Type:"));
        legendPanel.add(tileTypeDropdown);
        legendPanel.add(colorPickerButton);

        // Combine panels
        leftPanel.add(inputPanel, BorderLayout.NORTH);
        leftPanel.add(legendPanel, BorderLayout.CENTER);

        // Panel kanan untuk preview
        previewPanel = new JPanel();
        previewPanel.setBorder(BorderFactory.createTitledBorder("Preview"));
        previewPanel.setLayout(new GridLayout(1, 1));

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            LandingPageUI landingPageUI = new LandingPageUI();
            landingPageUI.setVisible(true);
            dispose();
        });

        JButton startButton = new JButton("Start the Game");
        startButton.addActionListener(this::startGame);

        bottomPanel.add(backButton);
        bottomPanel.add(startButton);

        // Add all panels
        add(leftPanel, BorderLayout.WEST);
        add(previewPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void initializeDefaultTileColors() {
        for (ETileType type : ETileType.values()) {
            tileColors.put(type, Color.GRAY); // Default color
        }
    }

    private void generatePreview(ActionEvent e) {
        try {
            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());

            previewPanel.removeAll();
            JPanel gridPanel = new JPanel(new GridLayout(height, width));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    JLabel tileLabel = new JLabel("", SwingConstants.CENTER);
                    tileLabel.setOpaque(true);
                    tileLabel.setBackground(Color.LIGHT_GRAY);
                    tileLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    gridPanel.add(tileLabel);
                }
            }
            previewPanel.add(gridPanel);
            previewPanel.revalidate();
            previewPanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Width and Height must be valid integers.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startGame(ActionEvent e) {
        // try {
        //     int width = Integer.parseInt(widthField.getText());
        //     int height = Integer.parseInt(heightField.getText());

        //     // Create game board with settings
        //     BoardUI boardUI = new BoardUI(width, height, "Custom Map", tileColors);
        //     boardUI.setVisible(true);
        //     dispose();
        // } catch (NumberFormatException ex) {
        //     JOptionPane.showMessageDialog(this, "Width and Height must be valid integers.", "Error",
        //             JOptionPane.ERROR_MESSAGE);
        // }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MapSettingsUI mapSettingsUI = new MapSettingsUI();
            mapSettingsUI.setVisible(true);
        });
    }
}
