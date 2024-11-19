package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.MapGenerator;
import com.coffeeisoxigen.model.tile.ETileType;

public class DrawingPageLogic {
    private DrawingPageLayout drawingLayout;
    private DrawingPageUI drawingPageUI;
    private Board board;

    public DrawingPageLogic(DrawingPageLayout drawingLayout, DrawingPageUI drawingPageUI) {
        this.drawingLayout = drawingLayout;
        this.drawingPageUI = drawingPageUI;
    }

    public void generatePreview(ActionEvent e) {
        if (board != null) {
            showErrorDialog("Board already created. Please reset to create a new board.");
            return;
        }

        int width, height;
        try {
            width = Integer.parseInt(drawingLayout.getTopPanel(this).getWidthText());
            height = Integer.parseInt(drawingLayout.getTopPanel(this).getHeightText());
        } catch (NumberFormatException ex) {
            showErrorDialog("Invalid width or height input.");
            return;
        }

        String name = drawingLayout.getTopPanel(this).getNameText();
        if (name.isEmpty()) {
            showErrorDialog("Map name cannot be empty.");
            return;
        }

        // Use SwingWorker to handle board creation and animation in a background thread
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                createBoard(name, width, height);
                return null;
            }

            @Override
            protected void done() {
                Map<ETileType, Color> tileColors = drawingLayout.getLegendPanel().getTileColors();
                DrawMapAnimator animator = new DrawMapAnimator(drawingLayout.getCenterPanel(), board, tileColors);
                animator.animateMapCreation(width, height);

                // Update total tiles in legend panel
                drawingLayout.getLegendPanel().setTotalTiles(board.getWidth() * board.getHeight());

                // Disable the generate button
                drawingLayout.getTopPanel(null).setGenerateButtonEnabled(false);
            }
        }.execute();
    }

    public void resetBoard(ActionEvent e) {
        board = null;
        drawingLayout.getCenterPanel().resetTilePanels();
        drawingLayout.getLegendPanel().setTotalTiles(0);
        drawingLayout.getTopPanel(null).setGenerateButtonEnabled(true);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(drawingPageUI, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void createBoard(String name, int width, int height) {
        MapGenerator generator = new MapGenerator();
        generator.createNewMap(name, width, height, false);
        board = generator.getBoard();
    }
}