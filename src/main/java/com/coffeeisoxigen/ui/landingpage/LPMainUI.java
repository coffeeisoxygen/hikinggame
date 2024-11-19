package com.coffeeisoxigen.ui.landingpage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.coffeeisoxigen.model.board.IMapGenerator;
import com.coffeeisoxigen.model.board.MapGenerator;

public class LPMainUI extends JFrame {
    private IMapGenerator mapGenerator;
    private LPLayout landingLayout;
    private LPLogic landingLogic;

    public LPMainUI() {
        mapGenerator = new MapGenerator(); // Use the interface
        landingLayout = new LPLayout();
        landingLogic = new LPLogic(mapGenerator, this);

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
            LPMainUI landingPageUI = new LPMainUI();
            landingPageUI.setVisible(true);
        });
    }
}