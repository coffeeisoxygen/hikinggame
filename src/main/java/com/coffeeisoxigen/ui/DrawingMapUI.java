package com.coffeeisoxigen.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.ETileType;
import com.coffeeisoxigen.model.board.MapGenerator;

public class DrawingMapUI extends JFrame {
    private JTextField widthField;
    private JTextField heightField;
    private JTextField nameField;
    private JPanel previewPanel;
    private JPanel legendPanel;
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

    // Initialize default colors for tile types
    private void initializeTileColors() {
        tileColors = new HashMap<>();
        for (ETileType type : ETileType.values()) {
            tileColors.put(type, Color.GRAY); // Default color
        }
    }

    // Create top panel for inputs
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new GridLayout(2, 4, 5, 5));

        JLabel widthLabel = new JLabel("Width:");
        widthField = new JTextField();
        JLabel heightLabel = new JLabel("Height:");
        heightField = new JTextField();
        JLabel nameLabel = new JLabel("Map Name:");
        nameField = new JTextField();

        generateButton = new JButton("Generate Map");
        generateButton.addActionListener(e -> generatePreview());

        topPanel.add(widthLabel);
        topPanel.add(widthField);
        topPanel.add(heightLabel);
        topPanel.add(heightField);
        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(new JLabel()); // Spacer
        topPanel.add(generateButton);

        // JCheckBox showLabelCheckbox = new JCheckBox("Show Labels");
        // showLabelCheckbox.setSelected(true);
        // showLabelCheckbox.addActionListener(e -> generatePreview());
        // topPanel.add(showLabelCheckbox);

        return topPanel;
    }

    // Create center panel for preview
    private JPanel createCenterPanel() {
        previewPanel = new JPanel();
        previewPanel.setLayout(new GridLayout(1, 1)); // Placeholder layout
        previewPanel.setBorder(BorderFactory.createTitledBorder("Map Preview"));
        return previewPanel;
    }

    // Create bottom panel for legend
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

    // Generate preview map with animation
    private void generatePreview() {
        int width, height;
        try {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid width or height input.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = nameField.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Map name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create board model
        MapGenerator generator = new MapGenerator();
        generator.createMap(name, width, height, false);
        board = generator.getBoard();

        // Clear and set grid for preview
        previewPanel.removeAll();
        previewPanel.setLayout(new GridLayout(height, width));

        // SwingWorker for progressive rendering with labels
        SwingWorker<Void, JPanel> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        JPanel tilePanel = new JPanel(new BorderLayout());
                        tilePanel.setBackground(Color.GRAY); // Default color
                        tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        // Add label to tile
                        JLabel tileLabel = new JLabel("(" + x + ", " + y + ")", SwingConstants.CENTER);
                        tileLabel.setForeground(Color.WHITE); // Adjust contrast
                        tilePanel.add(tileLabel, BorderLayout.CENTER);

                        publish(tilePanel);

                        try {
                            Thread.sleep(50); // Simulate animation delay
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
                return null;
            }

            @Override
            protected void process(java.util.List<JPanel> chunks) {
                for (JPanel panel : chunks) {
                    previewPanel.add(panel);
                    previewPanel.revalidate();
                    previewPanel.repaint();
                }
            }

            @Override
            protected void done() {
                try {
                    get(); // Wait for completion
                } catch (InterruptedException | ExecutionException ignored) {
                }
                generateButton.setEnabled(true);
            }
        };

        generateButton.setEnabled(false); // Disable button during generation
        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingMapUI ui = new DrawingMapUI();
            ui.setVisible(true);
        });
    }
}
