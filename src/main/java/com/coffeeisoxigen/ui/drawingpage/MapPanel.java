package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MapPanel extends JPanel {
    public MapPanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Preview"));
    }

    public void updatePreview(String mapName) {
        removeAll();
        JLabel preview = new JLabel("Map Preview: " + mapName);
        preview.setHorizontalAlignment(SwingConstants.CENTER);
        preview.setFont(new Font("Arial", Font.BOLD, 14));
        add(preview);
        revalidate();
        repaint();
    }
}
