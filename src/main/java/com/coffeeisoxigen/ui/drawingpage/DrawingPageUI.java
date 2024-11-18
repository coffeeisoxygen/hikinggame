package com.coffeeisoxigen.ui.drawingpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.ETileType;
import com.coffeeisoxigen.model.board.MapGenerator;
import com.coffeeisoxigen.model.board.Tile;

public class DrawingPageUI extends JFrame {
    private DrawPageTopPanel topPanel;
    private DrawPageCenterPanel centerPanel;
    private DrawPageLegendPanel legendPanel;
    private Map<ETileType, Color> tileColors;
    private Board board;

    public DrawingPageUI() {
        setTitle("Map Creator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeTileColors();
        topPanel = new DrawPageTopPanel(e -> generatePreview());
        centerPanel = new DrawPageCenterPanel();
        legendPanel = new DrawPageLegendPanel(tileColors);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(legendPanel, BorderLayout.SOUTH);
    }

    private void initializeTileColors() {
        tileColors = new HashMap<>();
        for (ETileType type : ETileType.values()) {
            tileColors.put(type, type.getDefaultColor());
        }
    }

    private void generatePreview() {
        int width, height;
        try {
            width = Integer.parseInt(topPanel.getWidthText());
            height = Integer.parseInt(topPanel.getHeightText());
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid width or height input.");
            return;
        }

        String name = topPanel.getNameText();
        if (name.isEmpty()) {
            showErrorDialog("Map name cannot be empty.");
            return;
        }

        createBoard(name, width, height);
        centerPanel.setupPreviewPanel(width, height);
        startPreviewGeneration(width, height);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void createBoard(String name, int width, int height) {
        MapGenerator generator = new MapGenerator();
        generator.createMap(name, width, height, false);
        board = generator.getBoard();
    }

    private void startPreviewGeneration(int width, int height) {
        SwingWorker<Void, JPanel> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        JPanel tilePanel = createTilePanel(x, y);
                        publish(tilePanel);
                    }
                }
                return null;
            }

            @Override
            protected void process(java.util.List<JPanel> chunks) {
                Timer timer = new Timer(50, e -> {
                    if (!chunks.isEmpty()) {
                        JPanel panel = chunks.remove(0);
                        centerPanel.addTilePanel(panel);
                    } else {
                        ((Timer) e.getSource()).stop();
                        topPanel.setGenerateButtonEnabled(true);
                    }
                });
                timer.start();
            }

            @Override
            protected void done() {
                topPanel.setGenerateButtonEnabled(true);
            }
        };

        topPanel.setGenerateButtonEnabled(false); // Disable button during generation
        worker.execute(); // Start background task
    }

    private JPanel createTilePanel(int x, int y) {
        Tile tile = board.getTile(x, y);
        JPanel tilePanel = new JPanel(new BorderLayout());
        tilePanel.setBackground(tileColors.get(tile.getTileType()));
        tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel tileLabel = new JLabel("(" + x + ", " + y + ")", SwingConstants.CENTER);
        tileLabel.setForeground(Color.WHITE); // Adjust contrast
        tilePanel.add(tileLabel, BorderLayout.CENTER);

        return tilePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingPageUI ui = new DrawingPageUI();
            ui.setVisible(true);
        });
    }
}