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
import com.coffeeisoxigen.model.board.MapGenerator;
import com.coffeeisoxigen.model.tile.Tile;

public class BoardUI extends JFrame {
    private Board board;
    private JPanel boardPanel;
    private JPanel controlPanel;
    private MapGenerator mapGenerator;

    public BoardUI() {
        this(10, 10, "Default Map", "#808080");
    }

    public BoardUI(int width, int height, String name, String color) {
        mapGenerator = new MapGenerator();
        setLayout(new BorderLayout());
        initializeUI(width, height, name, color);
    }

    private void initializeUI(int width, int height, String name, String color) {
        setTitle(name);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Control Panel (Top)
        controlPanel = new JPanel();
        JButton createMapButton = new JButton("Create Map");
        JButton loadMapButton = new JButton("Load Map");

        createMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMap(width, height, color);
            }
        });

        loadMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMap();
            }
        });

        controlPanel.add(createMapButton);
        controlPanel.add(loadMapButton);

        add(controlPanel, BorderLayout.NORTH);

        // Board Panel (Center)
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridBagLayout());
        add(boardPanel, BorderLayout.CENTER);
    }

    private void createMap(int width, int height, String color) {
        mapGenerator.createMap(width, height, "Custom Map", color);
        board = mapGenerator.getBoard();
        initializeBoardPanel(color);
    }

    private void loadMap() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                board = mapGenerator.loadMap(selectedFile);
                initializeBoardPanel("#808080"); // Default color for loaded maps
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading map: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void initializeBoardPanel(String color) {
        boardPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Tile tile = board.getTile(x, y);
                tile.setColor(color);
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