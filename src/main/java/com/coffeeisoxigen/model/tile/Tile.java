package com.coffeeisoxigen.model.tile;

import com.coffeeisoxigen.utils.Point;

public class Tile {
    private String name;
    private ETileType tileType;
    private Point position;
    private String color;

    public Tile(String name, ETileType tileType, Point position, String color) {
        this.name = name;
        this.tileType = tileType;
        this.position = position;
        this.color = color;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ETileType getTileType() {
        return tileType;
    }

    public void setTileType(ETileType tileType) {
        this.tileType = tileType;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}