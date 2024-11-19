package com.coffeeisoxigen.model.tile;

import com.coffeeisoxigen.utils.Point;

public class TileFactory {
    public Tile createTile(String name, ETileType type, Point position, String color) {
        return new Tile(name, type, position, color);
    }
}