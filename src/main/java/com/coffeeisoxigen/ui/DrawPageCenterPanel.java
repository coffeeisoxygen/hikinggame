package com.coffeeisoxigen.ui;

import javax.swing.*;
import java.awt.*;

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