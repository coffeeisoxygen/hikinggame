package com.coffeeisoxigen.ui.drawingpage;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
    private final BoardController boardController;
    private Point hoveredPoint; // Untuk melacak hover Tile sekarang

    public MapPanel(BoardController boardController) {
        this.boardController = boardController;
        setBackground(Color.WHITE);
        addMouseMotionListener(new TileHoverListener());
    }

    public void renderTiles() {
        removeAll();
        Board board = boardController.getBoard();
        if (board == null) {
            return;
        }

        setLayout(new GridLayout(board.getHeight(), board.getWidth()));
        Map<Point, Tile> tiles = board.getTiles();

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Point point = new Point(x, y);
                Tile tile = tiles.get(point);

                JPanel tilePanel = new TilePanel(tile, point);
                add(tilePanel);
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Panel khusus untuk Tile, mendukung overlay dan highlight.
     */
    private class TilePanel extends JPanel {
        private final Tile tile;
        private final Point position;

        public TilePanel(Tile tile, Point position) {
            this.tile = tile;
            this.position = position;
            setLayout(new BorderLayout());
            setBackground(tile != null ? tile.getTileType().getDefaultColor() : Color.GRAY);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));

            if (tile != null) {
                JLabel nameLabel = new JLabel(tile.getName(), SwingConstants.CENTER);
                JLabel coordLabel = new JLabel("(" + position.getPosX() + ", " + position.getPosY() + ")",
                        SwingConstants.CENTER);
                JLabel typeLabel = new JLabel(tile.getTileType().toString(), SwingConstants.CENTER);

                add(nameLabel, BorderLayout.NORTH);
                add(coordLabel, BorderLayout.CENTER);
                add(typeLabel, BorderLayout.SOUTH);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Overlay highlight saat hover
            if (position.equals(hoveredPoint)) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 255, 50)); // Biru transparan
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        }
    }

    /**
     * Listener untuk mendeteksi mouse hover di Tile.
     */
    private class TileHoverListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            Board board = boardController.getBoard();
            if (board == null)
                return;

            // Hitung posisi tile berdasarkan mouse
            int tileWidth = getWidth() / board.getWidth();
            int tileHeight = getHeight() / board.getHeight();
            int x = e.getX() / tileWidth;
            int y = e.getY() / tileHeight;

            Point newHoveredPoint = new Point(x, y);

            // Update hanya jika tile berubah
            if (!newHoveredPoint.equals(hoveredPoint)) {
                hoveredPoint = newHoveredPoint;
                repaint();
            }
        }
    }
}
