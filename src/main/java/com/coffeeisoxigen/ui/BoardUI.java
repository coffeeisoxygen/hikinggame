package com.coffeeisoxigen.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.MapGeneratorBasic;
import com.coffeeisoxigen.model.board.MapGeneratorCustom;
import com.coffeeisoxigen.model.tile.Tile;

public class BoardUI extends JFrame {
    private Board board;
    private JPanel boardPanel;
    private JPanel controlPanel;

    public BoardUI(Board board) {
        this.board = board;
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Game Board");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Control Panel (Top)
        controlPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton importMapButton = new JButton("Import Map");

        importMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importMap();
            }
        });

        controlPanel.add(startButton);
        controlPanel.add(importMapButton);

        add(controlPanel, BorderLayout.NORTH);

        // Board Panel (Center)
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridBagLayout());
        initializeBoardPanel();

        add(boardPanel, BorderLayout.CENTER);
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

    private void importMap() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Map Files", "map"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                board = loadBoardFromFile(selectedFile);
                initializeBoardPanel();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading map: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Board loadBoardFromFile(File file) throws IOException {
        // Implement the logic to load the board from the file
        // This is a placeholder implementation
        // You need to replace this with actual file reading logic
        int width = 6; // Example width
        int height = 12; // Example height
        Board board = new Board(width, height);
        // Populate the board with tiles from the file
        return board;
    }

    private class TilePanel extends JPanel {
        private final Tile tile;

        public TilePanel(Tile tile, int x, int y) {
            this.tile = tile;

            // Background color of the tile
            setBackground(Color.decode(tile.getColor()));

            // Border for the Tile
            setBorder(new LineBorder(Color.BLACK, 1)); // Thin black line
            setPreferredSize(new Dimension(50, 50));

            // Add label with newline and center alignment
            JLabel label = new JLabel("<html>"
                    + (x * board.getWidth() + y)
                    + "<br>(" + x + "," + y + ")</html>");
            label.setHorizontalAlignment(JLabel.CENTER); // Center horizontally
            label.setVerticalAlignment(JLabel.CENTER); // Center vertically
            add(label);
        }
    }
}