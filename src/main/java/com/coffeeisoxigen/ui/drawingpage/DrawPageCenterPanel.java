package com.coffeeisoxigen.ui.drawingpage;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawPageCenterPanel extends JPanel {
    public DrawPageCenterPanel() {
        setLayout(new GridLayout(1, 1)); // Placeholder layout
        setBorder(BorderFactory.createTitledBorder("Map Preview"));
    }

    public void setupPreviewPanel(int width, int height) {
        removeAll();
        setLayout(new GridLayout(height, width));
        revalidate();
        repaint();
    }

    public void addTilePanel(JPanel tilePanel) {
        add(tilePanel);
        revalidate();
        repaint();
    }
}