package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.coffeeisoxigen.controller.BoardController;
import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;

public class MapPanel extends JPanel {
    private BoardController boardController;

    public MapPanel(BoardController boardController) {
        this.boardController = boardController;
        setBackground(Color.WHITE);
    }

    public void renderTiles() {
        removeAll();
        Board board = boardController.getBoard();
        if (board == null) {
            return;
        }

        setLayout(new GridLayout(board.getHeight(), board.getWidth()));
        Map<Point, Tile> tiles = board.getTiles();

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Tile tile = tiles.get(new Point(x, y));
                if (tile != null) {
                    JPanel tilePanel = new JPanel();
                    tilePanel.setBackground(Color.decode(tile.getColor()));
                    tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    tilePanel.add(new JLabel(tile.getName()));
                    add(tilePanel);
                }
            }
        }

        revalidate();
        repaint();
    }
}