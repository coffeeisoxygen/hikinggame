package com.coffeeisoxigen.ui.landingpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LPLayout {
    private JPanel headerPanel;
    private JPanel buttonPanel;

    public LPLayout() {
        // Header Panel
        headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Hiking Game");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(headerLabel);
        headerPanel.setBackground(new Color(60, 63, 65)); // Dark gray background
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Padding

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Grid with spacing
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public JPanel getButtonPanel(LPLogic landingLogic) {
        // Buttons with custom style
        JButton createMapButton = new JButton("Create Your Own Map");
        JButton defaultMapButton = new JButton("Start with Default Map");
        JButton loadSavedMapButton = new JButton("Load Your Saved Map");
        JButton exitButton = new JButton("Exit");

        // Button actions
        createMapButton.addActionListener(landingLogic::createMapAction);
        defaultMapButton.addActionListener(landingLogic::defaultMapAction);
        loadSavedMapButton.addActionListener(landingLogic::loadSavedMapAction);
        exitButton.addActionListener(landingLogic::exitAction);

        // Add buttons to the button panel
        buttonPanel.add(createMapButton);
        buttonPanel.add(defaultMapButton);
        buttonPanel.add(loadSavedMapButton);
        buttonPanel.add(exitButton);

        return buttonPanel;
    }
}