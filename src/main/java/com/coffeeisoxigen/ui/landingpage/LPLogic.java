package com.coffeeisoxigen.ui.landingpage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.IMapGenerator;
import com.coffeeisoxigen.model.board.MapGenerator;
import com.coffeeisoxigen.ui.drawingpage.DrawingPageUI;
import com.coffeeisoxigen.ui.previewpage.PreviewPageUI;

public class LPLogic {
    private IMapGenerator mapGenerator;
    private LPMainUI landingPageUI;

    public LPLogic(IMapGenerator mapGenerator, LPMainUI landingPageUI) {
        this.mapGenerator = mapGenerator;
        this.landingPageUI = landingPageUI;
    }

    public void createMapAction(ActionEvent e) {
        DrawingPageUI mapSettingsUI = new DrawingPageUI();
        mapSettingsUI.setVisible(true);
        landingPageUI.dispose();
    }

    public void defaultMapAction(ActionEvent e) {
        mapGenerator.createMap();
        Board board = ((MapGenerator) mapGenerator).getBoard();
        PreviewPageUI previewUI = new PreviewPageUI(board);
        previewUI.setVisible(true);
        landingPageUI.dispose();
    }

    public void customMapAction(String name, int width, int height, boolean isProtected) {
        mapGenerator.createMap(name, width, height);
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
}