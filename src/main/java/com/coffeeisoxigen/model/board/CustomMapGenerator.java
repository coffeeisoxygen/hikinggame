package com.coffeeisoxigen.model.board;

import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;

public class CustomMapGenerator implements IMapGenerator {
    @Override
    public Board generateMap(int width, int height) {
        Board board = new Board(width, height);
        // Custom logic to initialize tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ETileType tileType = determineTileType(x, y);
                board.setTile(x, y, new Tile("Tile" + x + y, tileType, new Point(x, y), tileType.getColor(), "image.png"));
            }
        }
        return board;
    }

    @Override
    public int getDefaultWidth() {
        return 10;
    }

    @Override
    public int getDefaultHeight() {
        return 10;
    }

    private ETileType determineTileType(int x, int y) {
        // Custom logic to determine tile type based on position
        return ETileType.NORMAL;
    }
}