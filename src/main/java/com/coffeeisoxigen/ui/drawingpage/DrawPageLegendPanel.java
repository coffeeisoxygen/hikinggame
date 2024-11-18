package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.coffeeisoxigen.model.board.ETileType;

public class DrawPageLegendPanel extends JPanel {
    public DrawPageLegendPanel(Map<ETileType, Color> tileColors) {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Legend"));

        for (ETileType type : tileColors.keySet()) {
            JPanel legendItem = new JPanel();
            legendItem.setPreferredSize(new Dimension(30, 30));
            legendItem.setBackground(tileColors.get(type));
            add(new JLabel(type.name()));
            add(legendItem);
        }
    }
}