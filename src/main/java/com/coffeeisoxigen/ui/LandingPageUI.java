package com.coffeeisoxigen.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.MapGenerator;

public class LandingPageUI extends JFrame {
    public LandingPageUI() {
        setTitle("Landing Page");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window
        setResizable(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmExit();
            }
        });

        // Header Panel
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Welcome to Game Editor");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(headerLabel);
        headerPanel.setBackground(new Color(60, 63, 65)); // Dark gray background
        headerLabel.setForeground(Color.WHITE);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Grid with spacing
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Padding

        // Buttons with custom style
        JButton createMapButton = createStyledButton("Create Your Own Map");
        JButton defaultMapButton = createStyledButton("Start with Default Map");
        JButton loadSavedMapButton = createStyledButton("Load Your Saved Map");
        JButton exitButton = createStyledButton("Exit");

        // Button actions
        createMapButton.addActionListener((ActionEvent e) -> {
            DrawingMapUI mapSettingsUI = new DrawingMapUI();
            mapSettingsUI.setVisible(true);
            dispose();
        });

        defaultMapButton.addActionListener((ActionEvent e) -> {
            MapGenerator mapGenerator = new MapGenerator();
            mapGenerator.createMap("Default Map", 10, 10, true);
            Board board = mapGenerator.getBoard();
            PreviewUI previewUI = new PreviewUI(board);
            previewUI.setVisible(true);
            dispose();
        });

        loadSavedMapButton.addActionListener((ActionEvent e) -> {
            // Implement loading saved map logic here
        });

        exitButton.addActionListener((ActionEvent e) -> {
            confirmExit();
        });

        // Add buttons to the button panel
        buttonPanel.add(createMapButton);
        buttonPanel.add(defaultMapButton);
        buttonPanel.add(loadSavedMapButton);
        buttonPanel.add(exitButton);

        // Add panels to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void confirmExit() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        button.setBackground(new Color(30, 144, 255)); // Dodger blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(25, 120, 210), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 120, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255));
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LandingPageUI landingPageUI = new LandingPageUI();
            landingPageUI.setVisible(true);
        });
    }
}