package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;
import java.util.Map;

import javax.swing.JPanel;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.ETileType;
import com.coffeeisoxigen.model.board.Tile;

public class DrawMapAnimator {
    private DrawPageCenterPanel centerPanel;
    private Board board;
    private Map<ETileType, Color> tileColors;

    public DrawMapAnimator(DrawPageCenterPanel centerPanel, Board board, Map<ETileType, Color> tileColors) {
        this.centerPanel = centerPanel;
        this.board = board;
        this.tileColors = tileColors;
    }

    public void animateMapCreation(int width, int height) {
        centerPanel.setupPreviewPanel(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = board.getTile(x, y);
                ETileType tileType = tile.getTileType();
                Color color = tileColors.get(tileType);
                JPanel tilePanel = centerPanel.createTilePanel(tile.getIndex(), x, y, color);
                centerPanel.addTilePanel(tilePanel, x, y);
            }
        }
    }
}