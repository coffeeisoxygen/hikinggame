package com.coffeeisoxigen.model.board;

import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;

public class Board {
    private Tile[][] tiles;

    public Board(int width, int height) {
        this.tiles = new Tile[width][height];
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void setTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

    public boolean isInMap(Point point) {
        return point.getX() >= 0 && point.getX() < tiles.length && point.getY() >= 0 && point.getY() < tiles[0].length;
    }
}