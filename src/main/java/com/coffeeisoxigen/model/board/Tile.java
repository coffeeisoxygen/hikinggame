package com.coffeeisoxigen.model.board;

import com.coffeeisoxigen.utils.Point;

public class Tile {
    private String name;
    private ETileType tileType;
    private Point position;
    private String color;
    private String image;

    public Tile(String name, ETileType tileType, Point position, String color, String image) {
        this.name = (name == null || name.isEmpty()) ? "unnamed" : name;
        this.tileType = tileType;
        this.position = position;
        this.color = color;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}