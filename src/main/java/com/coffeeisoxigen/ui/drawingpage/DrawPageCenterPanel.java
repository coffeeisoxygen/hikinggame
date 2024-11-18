package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawPageCenterPanel extends JPanel {
    private Map<String, JPanel> tilePanels;

    public DrawPageCenterPanel() {
        setLayout(new GridLayout(1, 1)); // Placeholder layout
        setBorder(BorderFactory.createTitledBorder("Map Preview"));
        tilePanels = new HashMap<>();
    }

    public void setupPreviewPanel(int width, int height) {
        removeAll(); // Clear all components
        setLayout(new GridLayout(height, width)); // Set GridLayout sesuai map
        revalidate();
        repaint();
    }

    public void addTilePanel(JPanel tilePanel, int x, int y) {
        String key = x + "," + y; // Key dengan koordinat tile
        tilePanels.put(key, tilePanel); // Simpan panel di map berdasarkan key
        add(tilePanel); // Add panel ke grid
        revalidate();
        repaint();
    }

    public JPanel createTilePanel(int index, int x, int y, Color color) {
        JPanel tilePanel = new JPanel(new GridBagLayout());
        tilePanel.setBackground(color);
        tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border to each tile

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel indexLabel = new JLabel(String.valueOf(index));
        indexLabel.setHorizontalAlignment(JLabel.CENTER);
        indexLabel.setFont(indexLabel.getFont().deriveFont(16f)); // Larger font for index

        JLabel coordLabel = new JLabel("(" + x + "," + y + ")");
        coordLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setOpaque(false);
        textPanel.add(indexLabel, gbc);

        gbc.gridy = 1;
        textPanel.add(coordLabel, gbc);

        tilePanel.add(textPanel, gbc);

        return tilePanel;
    }

    public JPanel getTilePanel(int x, int y) {
        String key = x + "," + y; // Key berdasarkan koordinat
        return tilePanels.get(key); // Ambil panel berdasarkan key
    }

    public void resetTilePanels() {
        tilePanels.clear(); // Clear peta panel
        removeAll(); // Reset grid layout
    }
}