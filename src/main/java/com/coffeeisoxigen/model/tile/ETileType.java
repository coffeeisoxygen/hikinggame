package com.coffeeisoxigen.model.tile;

public enum ETileType {
    SAFE("#00FF00"), // Green
    DANGER("#FF0000"), // Red
    NORMAL("#808080"), // Gray
    START("#0000FF"), // Blue
    END("#FFFF00"); // Yellow

    private final String color;

    ETileType(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}