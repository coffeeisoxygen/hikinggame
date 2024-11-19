package com.coffeeisoxigen.model.tile;

import java.awt.Color;

public enum ETileType {
    SAFE,
    DANGER,
    NORMAL,
    START,
    END;

    public Color getDefaultColor() {
        switch (this) {
            case SAFE:
                return Color.YELLOW; // Color.BLUE;
            case DANGER:
                return Color.RED;
            case START:
                return Color.MAGENTA;
            case END:
                return Color.GREEN;
            case NORMAL:
            default:
                return Color.GRAY;
        }
    }
}