package com.coffeeisoxigen.model.board;

import java.util.HashMap;
import java.util.Map;

import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;

public class Board {
    private final String name;
    private final int width;
    private final int height;
    private final boolean isProtected;
    private final Map<Point, Tile> tiles;

    public Board(String name, int width, int height, boolean isProtected) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.isProtected = isProtected;
        this.tiles = new HashMap<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public Tile getTile(int x, int y) {
        return tiles.get(new Point(x, y));
    }

    public void setTile(int x, int y, Tile tile) {
        tiles.put(new Point(x, y), tile);
    }

    public boolean isInMap(Point point) {
        return point.getPosX() >= 0 && point.getPosX() < width && point.getPosY() >= 0 && point.getPosY() < height;
    }

    public Map<Point, Tile> getTiles() {
        return tiles;
    }
}