package com.coffeeisoxigen.ui.landingpage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.IMapCreatable;
import com.coffeeisoxigen.model.board.MapGenerator;
import com.coffeeisoxigen.ui.drawingpage.DrawingPageUI;
import com.coffeeisoxigen.ui.previewpage.PreviewPageUI;

public class LogicLandingPage {
    private IMapCreatable mapGenerator;
    private UIMainLandingPage landingPageUI;

    public LogicLandingPage(IMapCreatable mapGenerator, UIMainLandingPage landingPageUI) {
        this.mapGenerator = mapGenerator;
        this.landingPageUI = landingPageUI;
    }

    public void createMapAction(ActionEvent e) {
        DrawingPageUI mapSettingsUI = new DrawingPageUI();
        mapSettingsUI.setVisible(true);
        landingPageUI.dispose();
    }

    public void defaultMapAction(ActionEvent e) {
        mapGenerator.createDefaultMap();
        Board board = ((MapGenerator) mapGenerator).getBoard();
        PreviewPageUI previewUI = new PreviewPageUI(board);
        previewUI.setVisible(true);
        landingPageUI.dispose();
    }

    public void loadSavedMapAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                mapGenerator.loadSavedMap(selectedFile);
                Board board = ((MapGenerator) mapGenerator).getBoard();
                PreviewPageUI previewUI = new PreviewPageUI(board);
                previewUI.setVisible(true);
                landingPageUI.dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(landingPageUI, "Error loading map: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void exitAction(ActionEvent e) {
        confirmExit();
    }

    public void confirmExit() {
        int response = JOptionPane.showConfirmDialog(landingPageUI, "Are you sure you want to exit?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Helper method to create styled buttons
    public JButton createStyledButton(String text) {
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
}