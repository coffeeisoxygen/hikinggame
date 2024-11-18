package com.coffeeisoxigen;

import javax.swing.SwingUtilities;

import com.coffeeisoxigen.ui.landingpage.LandingPageUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LandingPageUI landingPageUI = new LandingPageUI();
            landingPageUI.setVisible(true);
        });
    }
}