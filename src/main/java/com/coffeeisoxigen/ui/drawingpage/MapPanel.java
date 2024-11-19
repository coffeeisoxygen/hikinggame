package com.coffeeisoxigen.ui.drawingpage;

import javax.swing.*;
import java.awt.*;
import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.tile.Tile;

public class MapPanel extends JPanel {
    public MapPanel() {
        setLayout(new GridLayout(1, 1)); // Default layout (empty)
    }

    public void updatePreview(Board board) {
        removeAll(); // Clear panel

        if (board == null) {
            revalidate();
            repaint();
            return; // Nothing to display
        }

        setLayout(new GridLayout(board.getHeight(), board.getWidth())); // Update layout

        Tile[][] tiles = board.getTiles();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Tile tile = tiles[x][y];
                JPanel tilePanel = createTilePanel(tile, x, y);
                add(tilePanel);
            }
        }

        revalidate();
        repaint();
    }

    private JPanel createTilePanel(Tile tile, int x, int y) {
        JPanel tilePanel = new JPanel();
        tilePanel.setBackground(tile.getTileType().getDefaultColor());
        tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel label = new JLabel(String.format("<html>#%d<br>%s<br>(%d,%d)</html>", 
            tile.getId(), tile.getTileType(), x, y));
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        tilePanel.add(label);

        return tilePanel;
    }
}
