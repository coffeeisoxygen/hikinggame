package com.coffeeisoxigen.model.board;

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
                return Color.GREEN;
            case DANGER:
                return Color.RED;
            case START:
                return Color.BLUE;
            case END:
                return Color.YELLOW;
            case NORMAL:
            default:
                return Color.GRAY;
        }
    }
}