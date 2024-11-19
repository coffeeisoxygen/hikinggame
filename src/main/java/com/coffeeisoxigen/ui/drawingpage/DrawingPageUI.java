package com.coffeeisoxigen.ui.drawingpage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.coffeeisoxigen.controller.BoardController;
import com.coffeeisoxigen.model.board.Board;

public class DrawingPageUI extends JFrame {
    private MapPanel mapPanel;
    private ControlPanel controlPanel;
    private LegendPanel legendPanel;
    private BoardController boardController;

    public DrawingPageUI(BoardController boardController) {
        this.boardController = boardController;
        setTitle("Map Generator");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // MapPanel
        mapPanel = new MapPanel(boardController);
        mainPanel.add(mapPanel, BorderLayout.CENTER);

        // ControlPanel
        controlPanel = new ControlPanel();
        controlPanel.getGenerateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateMap();
            }
        });
        controlPanel.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMap();
            }
        });
        mainPanel.add(controlPanel, BorderLayout.NORTH);

        // LegendPanel
        legendPanel = new LegendPanel();
        mainPanel.add(legendPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void generateMap() {
        String name = controlPanel.getNameField().getText();
        int width = Integer.parseInt(controlPanel.getWidthField().getText());
        int height = Integer.parseInt(controlPanel.getHeightField().getText());
        boardController.createCustomMap(name, width, height);
        mapPanel.renderTiles();
        legendPanel.updateTotalTiles(boardController.getTotalTiles());
    }

    private void resetMap() {
        boardController.resetMap();
        mapPanel.renderTiles();
        legendPanel.updateTotalTiles(boardController.getTotalTiles());
    }
}