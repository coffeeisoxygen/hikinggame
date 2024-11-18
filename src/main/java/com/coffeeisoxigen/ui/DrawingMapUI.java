package com.coffeeisoxigen.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.ETileType;
import com.coffeeisoxigen.model.board.MapGenerator;

public class DrawingMapUI extends JFrame {
    private JTextField widthField, heightField, nameField;
    private JPanel previewPanel, legendPanel;
    private Map<ETileType, Color> tileColors;
    private Board board;
    private JButton generateButton;

    public DrawingMapUI() {
        setTitle("Map Creator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeTileColors();
        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private void initializeTileColors() {
        tileColors = new HashMap<>();
        for (ETileType type : ETileType.values()) {
            tileColors.put(type, Color.GRAY); // Default color
        }
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        widthField = new JTextField();
        heightField = new JTextField();
        nameField = new JTextField();
        generateButton = new JButton("Generate Map");
        generateButton.addActionListener(e -> generatePreview());

        topPanel.add(new JLabel("Width:"));
        topPanel.add(widthField);
        topPanel.add(new JLabel("Height:"));
        topPanel.add(heightField);
        topPanel.add(new JLabel("Map Name:"));
        topPanel.add(nameField);
        topPanel.add(new JLabel()); // Spacer
        topPanel.add(generateButton);

        return topPanel;
    }

    private JPanel createCenterPanel() {
        previewPanel = new JPanel();
        previewPanel.setLayout(new GridLayout(1, 1)); // Placeholder layout
        previewPanel.setBorder(BorderFactory.createTitledBorder("Map Preview"));
        return previewPanel;
    }

    private JPanel createBottomPanel() {
        legendPanel = new JPanel(new FlowLayout());
        legendPanel.setBorder(BorderFactory.createTitledBorder("Legend"));

        for (ETileType type : tileColors.keySet()) {
            JPanel legendItem = new JPanel();
            legendItem.setPreferredSize(new Dimension(30, 30));
            legendItem.setBackground(tileColors.get(type));
            legendPanel.add(new JLabel(type.name()));
            legendPanel.add(legendItem);
        }

        return legendPanel;
    }

    private void generatePreview() {
        int width, height;
        try {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid width or height input.");
            return;
        }

        String name = nameField.getText();
        if (name.isEmpty()) {
            showErrorDialog("Map name cannot be empty.");
            return;
        }

        createBoard(name, width, height);
        setupPreviewPanel(width, height);
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

    private void setupPreviewPanel(int width, int height) {
        previewPanel.removeAll();
        previewPanel.setLayout(new GridLayout(height, width));
        previewPanel.revalidate();
        previewPanel.repaint();
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
                        previewPanel.add(panel);
                        previewPanel.revalidate();
                        previewPanel.repaint();
                    } else {
                        ((Timer) e.getSource()).stop();
                        generateButton.setEnabled(true);
                    }
                });
                timer.start();
            }

            @Override
            protected void done() {
                generateButton.setEnabled(true);
            }
        };

        generateButton.setEnabled(false); // Disable button during generation
        worker.execute(); // Start background task
    }

    private JPanel createTilePanel(int x, int y) {
        JPanel tilePanel = new JPanel(new BorderLayout());
        tilePanel.setBackground(Color.GRAY); // Default color
        tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel tileLabel = new JLabel("(" + x + ", " + y + ")", SwingConstants.CENTER);
        tileLabel.setForeground(Color.WHITE); // Adjust contrast
        tilePanel.add(tileLabel, BorderLayout.CENTER);

        return tilePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingMapUI ui = new DrawingMapUI();
            ui.setVisible(true);
        });
    }
}
