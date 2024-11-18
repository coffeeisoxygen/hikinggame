package com.coffeeisoxigen.ui.drawingpage;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DrawingPageUI extends JFrame {
    private DrawingPageLayout drawingLayout;
    private DrawingPageLogic drawingLogic;

    public DrawingPageUI() {
        setTitle("Map Creator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingLayout = new DrawingPageLayout();
        drawingLogic = new DrawingPageLogic(drawingLayout, this);

        drawingLayout.getTopPanel(drawingLogic).setGenerateAction(drawingLogic::generatePreview);
        drawingLayout.getTopPanel(drawingLogic).setResetAction(drawingLogic::resetBoard);

        add(drawingLayout.getTopPanel(drawingLogic), BorderLayout.NORTH);
        add(drawingLayout.getCenterPanel(), BorderLayout.CENTER);
        add(drawingLayout.getLegendPanel(), BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingPageUI ui = new DrawingPageUI();
            ui.setVisible(true);
        });
    }
}