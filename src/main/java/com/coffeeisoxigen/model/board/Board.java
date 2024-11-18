package com.coffeeisoxigen.model.board;

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
        initializeDefaultTiles();
    }

    private void initializeDefaultTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ETileType type = ETileType.NORMAL;
                if (x == 0 && y == 0) {
                    type = ETileType.START;
                } else if (x == width - 1 && y == height - 1) {
                    type = ETileType.END;
                }
                tiles[x][y] = new Tile("Tile" + x + y, type, new Point(x, y), "#808080", "image.png");
            }
        }
    }

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