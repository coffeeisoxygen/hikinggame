package com.coffeeisoxigen.ui.landingpage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.coffeeisoxigen.model.board.IMapCreatable;
import com.coffeeisoxigen.model.board.MapGenerator;

public class UIMainLandingPage extends JFrame {
    private IMapCreatable mapGenerator;
    private LayoutLandingPage landingLayout;
    private LogicLandingPage landingLogic;

    public UIMainLandingPage() {
        mapGenerator = new MapGenerator(); // Use the interface
        landingLayout = new LayoutLandingPage();
        landingLogic = new LogicLandingPage(mapGenerator, this);

        setTitle("Landing Page");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window
        setResizable(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                landingLogic.confirmExit();
            }
        });

        // Add panels to the frame
        add(landingLayout.getHeaderPanel(), BorderLayout.NORTH);
        add(landingLayout.getButtonPanel(landingLogic), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIMainLandingPage landingPageUI = new UIMainLandingPage();
            landingPageUI.setVisible(true);
        });
    }
}