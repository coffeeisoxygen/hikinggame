package com.coffeeisoxigen.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.MapGeneratorFactory;
import com.coffeeisoxigen.model.tile.Tile;

public class BoardUI extends JFrame {
    private Board board;
    private JPanel boardPanel;
    private JPanel controlPanel;

    public BoardUI(Board board) {
        this.board = board;
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
        String[] mapTypes = MapGeneratorFactory.getAvailableMapTypes();
        JComboBox<String> mapTypeComboBox = new JComboBox<>(mapTypes);

        mapTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMapType = (String) mapTypeComboBox.getSelectedItem();
                int width = MapGeneratorFactory.getDefaultWidth(selectedMapType);
                int height = MapGeneratorFactory.getDefaultHeight(selectedMapType);
                board = MapGeneratorFactory.createMap(selectedMapType, width, height);
                updateBoardPanel();
            }
        });

        controlPanel.add(startButton);
        controlPanel.add(importMapButton);
        controlPanel.add(mapTypeComboBox);

        // Board Panel (Bottom)
        boardPanel = new JPanel(new GridLayout(board.getWidth(), board.getHeight())); // without spacing
        boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // padding around the panel

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Tile tile = board.getTile(x, y);
                TilePanel tilePanel = new TilePanel(tile);
                boardPanel.add(tilePanel);
                tile.addPropertyChangeListener(tilePanel);
            }
        }

        // SplitPane to divide the screen
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, controlPanel, boardPanel);
        splitPane.setDividerLocation(50); // Height of the top panel
        splitPane.setResizeWeight(0.1); // Proportion of size (10% top, 90% bottom)

        add(splitPane);
        setVisible(true);
    }

    private void updateBoardPanel() {
        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(board.getWidth(), board.getHeight()));
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Tile tile = board.getTile(x, y);
                TilePanel tilePanel = new TilePanel(tile);
                boardPanel.add(tilePanel);
                tile.addPropertyChangeListener(tilePanel);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private class TilePanel extends JPanel implements PropertyChangeListener {
        private Tile tile;

        public TilePanel(Tile tile) {
            this.tile = tile;

            // Background color of the tile
            setBackground(Color.decode(tile.getColor()));

            // Border for the Tile
            setBorder(new LineBorder(Color.BLACK, 1)); // Thin black line
            setPreferredSize(new Dimension(50, 50));
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("color".equals(evt.getPropertyName())) {
                setBackground(Color.decode((String) evt.getNewValue()));
                repaint();
            }
        }
    }
}