package com.coffeeisoxigen;

import javax.swing.SwingUtilities;

import com.coffeeisoxigen.ui.landingpage.LPMainUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LPMainUI landingPageUI = new LPMainUI();
            landingPageUI.setVisible(true);
        });
    }
}