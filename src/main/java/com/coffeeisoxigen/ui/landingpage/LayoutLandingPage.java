package com.coffeeisoxigen.ui.landingpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LayoutLandingPage {
    private JPanel headerPanel;
    private JPanel buttonPanel;

    public LayoutLandingPage() {
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
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Padding
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public JPanel getButtonPanel(LogicLandingPage landingLogic) {
        // Buttons with custom style
        JButton createMapButton = landingLogic.createStyledButton("Create Your Own Map");
        JButton defaultMapButton = landingLogic.createStyledButton("Start with Default Map");
        JButton loadSavedMapButton = landingLogic.createStyledButton("Load Your Saved Map");
        JButton exitButton = landingLogic.createStyledButton("Exit");

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