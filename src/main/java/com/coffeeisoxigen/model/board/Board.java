package com.coffeeisoxigen.model.board;

import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;

public class Board {
    private String name;
    private int width;
    private int height;
    private boolean isProtected;
    private Tile[][] tiles;

    public Board(String name, int width, int height, boolean isProtected) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.isProtected = isProtected;
        this.tiles = new Tile[width][height];
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
        return tiles[x][y];
    }

    public void setTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

    public boolean isInMap(Point point) {
        return point.getPosX() >= 0 && point.getPosX() < width && point.getPosY() >= 0 && point.getPosY() < height;
    }
}