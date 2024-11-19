package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.coffeeisoxigen.model.tile.ETileType;

public class LegendPanel extends JPanel {
    private JLabel totalTilesLabel;
    private Map<ETileType, JLabel> tileTypeLabels;

    public LegendPanel() {
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createTitledBorder("Legend"));
        setLayout(new GridLayout(ETileType.values().length + 1, 1));

        totalTilesLabel = new JLabel("Total Tiles: 0");
        add(totalTilesLabel);

        tileTypeLabels = new EnumMap<>(ETileType.class);
        for (ETileType type : ETileType.values()) {
            JLabel label = new JLabel(type.toString() + ": 0");
            tileTypeLabels.put(type, label);
            add(label);
        }
    }

    public void updateTotalTiles(int totalTiles) {
        totalTilesLabel.setText("Total Tiles: " + totalTiles);
    }

    public void updateTileTypeCount(Map<ETileType, Integer> tileTypeCounts) {
        for (Map.Entry<ETileType, Integer> entry : tileTypeCounts.entrySet()) {
            ETileType type = entry.getKey();
            int count = entry.getValue();
            JLabel label = tileTypeLabels.get(type);
            if (label != null) {
                label.setText(type.toString() + ": " + count);
            }
        }
    }
}