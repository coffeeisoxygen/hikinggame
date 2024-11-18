package com.coffeeisoxigen.ui.drawingpage;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.coffeeisoxigen.model.board.ETileType;

public class DrawingPageLayout {
    private DrawPageTopPanel topPanel;
    private DrawPageCenterPanel centerPanel;
    private DrawPageLegendPanel legendPanel;
    private Map<ETileType, Color> tileColors;

    public DrawingPageLayout() {
        initializeTileColors();
        topPanel = new DrawPageTopPanel(null, null); // ActionListeners will be set in DrawingPageUI
        centerPanel = new DrawPageCenterPanel();
        legendPanel = new DrawPageLegendPanel(tileColors);
    }

    private void initializeTileColors() {
        tileColors = new HashMap<>();
        for (ETileType type : ETileType.values()) {
            tileColors.put(type, type.getDefaultColor());
        }
    }

    public DrawPageTopPanel getTopPanel(DrawingPageLogic drawingLogic) {
        topPanel.setGenerateAction(drawingLogic::generatePreview);
        return topPanel;
    }

    public DrawPageCenterPanel getCenterPanel() {
        return centerPanel;
    }

    public DrawPageLegendPanel getLegendPanel() {
        return legendPanel;
    }
}