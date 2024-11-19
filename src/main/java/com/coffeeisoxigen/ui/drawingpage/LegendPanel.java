package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LegendPanel extends JPanel {
    private JLabel totalTilesLabel;

    public LegendPanel() {
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createTitledBorder("Legend"));
        totalTilesLabel = new JLabel("Total Tiles: 0");
        add(totalTilesLabel);
    }

    public void updateTotalTiles(int totalTiles) {
        totalTilesLabel.setText("Total Tiles: " + totalTiles);
        revalidate();
        repaint();
    }
}
