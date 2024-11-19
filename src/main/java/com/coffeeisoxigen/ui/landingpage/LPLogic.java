package com.coffeeisoxigen.ui.landingpage;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.coffeeisoxigen.controller.BoardController;
import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.ui.drawingpage.DrawingPageUI;
import com.coffeeisoxigen.ui.previewpage.PreviewPageUI;

public class LPLogic {
    private final BoardController boardController;
    private final LPMainUI landingPageUI;

    public LPLogic(BoardController boardController, LPMainUI landingPageUI) {
        this.boardController = boardController;
        this.landingPageUI = landingPageUI;
    }

    public void createMapAction(ActionEvent e) {
        DrawingPageUI mapSettingsUI = new DrawingPageUI(boardController);
        mapSettingsUI.setVisible(true);
        landingPageUI.dispose();
    }

    public void defaultMapAction(ActionEvent e) {
        boardController.createDefaultMap();
        Board board = boardController.getBoard();
        PreviewPageUI previewUI = new PreviewPageUI(board);
        previewUI.setVisible(true);
        landingPageUI.dispose();
    }

    public void customMapAction(String name, int width, int height, boolean isProtected) {
        boardController.createCustomMap(name, width, height);
        Board board = boardController.getBoard();
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
            boardController.loadSavedMap(selectedFile);
            Board board = boardController.getBoard();
            PreviewPageUI previewUI = new PreviewPageUI(board);
            previewUI.setVisible(true);
            landingPageUI.dispose();
            // TODO: Implement loadSavedMap method in BoardController
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