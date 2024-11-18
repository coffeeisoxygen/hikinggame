package com.coffeeisoxigen;

import javax.swing.SwingUtilities;

import com.coffeeisoxigen.ui.landingpage.UIMainLandingPage;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIMainLandingPage landingPageUI = new UIMainLandingPage();
            landingPageUI.setVisible(true);
        });
    }
}