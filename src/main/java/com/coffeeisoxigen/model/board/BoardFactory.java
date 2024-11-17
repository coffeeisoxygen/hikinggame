package com.coffeeisoxigen.model.board;

import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;
import com.coffeeisoxigen.model.tile.ETileType;

public class BoardFactory {
    public static Board createBoard(int width, int height) {
        Board board = new Board(width, height);
        // Initialize tiles with appropriate types
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ETileType tileType = determineTileType(x, y);
                board.setTile(x, y,
                        new Tile("Tile" + x + y, tileType, new Point(x, y), tileType.getColor(), "image.png"));
            }
        }
        return board;
    }

    private static ETileType determineTileType(int x, int y) {
        // Logic to determine tile type based on position
        return ETileType.NORMAL;
    }
}