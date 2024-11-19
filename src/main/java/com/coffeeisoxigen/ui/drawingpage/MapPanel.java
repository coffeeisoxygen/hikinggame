package com.coffeeisoxigen.ui.drawingpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
                    tilePanel.setBackground(tile.getTileType().getDefaultColor());
                    tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    tilePanel.setLayout(new BorderLayout());

                    JLabel nameLabel = new JLabel(tile.getName());
                    nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    tilePanel.add(nameLabel, BorderLayout.NORTH);

                    JLabel coordLabel = new JLabel("(" + x + ", " + y + ")");
                    coordLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    tilePanel.add(coordLabel, BorderLayout.CENTER);

                    JLabel typeLabel = new JLabel(tile.getTileType().toString());
                    typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    tilePanel.add(typeLabel, BorderLayout.SOUTH);

                    add(tilePanel);
                }
            }
        }

        revalidate();
        repaint();
    }
}