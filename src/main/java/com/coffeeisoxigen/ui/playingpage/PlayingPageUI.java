package com.coffeeisoxigen.ui.playingpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.tile.Tile;

public class PlayingPageUI extends JFrame {
    private Board board;
    private JPanel boardPanel;

    public PlayingPageUI(Board board) {
        this.board = board;
        setTitle("Game Board");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridBagLayout());
        add(boardPanel, BorderLayout.CENTER);

        initializeBoardPanel();
    }

    private void initializeBoardPanel() {
        boardPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Tile tile = board.getTile(x, y);
                gbc.gridx = x;
                gbc.gridy = y;
                boardPanel.add(new TilePanel(tile, x, y), gbc);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private class TilePanel extends JPanel {
        private final Tile tile;

        public TilePanel(Tile tile, int x, int y) {
            this.tile = tile;
            setBackground(Color.decode(tile.getColor()));
            setBorder(new LineBorder(Color.BLACK, 1));
            setPreferredSize(new Dimension(50, 50));
            JLabel label = new JLabel("<html>" + (x * board.getWidth() + y) + "<br>(" + x + "," + y + ")</html>");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            add(label);
        }
    }
}