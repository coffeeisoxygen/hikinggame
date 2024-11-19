package com.coffeeisoxigen.model.board.strategy;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.TileFactory;
import com.coffeeisoxigen.utils.Point;

public class CustomTilePlacement implements TileLayoutStrategy {
    @Override
    public void placeTiles(Board board, TileFactory tileFactory) {
        int rows = board.getWidth();
        int cols = board.getHeight();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                ETileType type = ETileType.SAFE;
                if (row == 0 && col == 0) {
                    type = ETileType.START;
                } else if (row == rows - 1 && col == cols - 1) {
                    type = ETileType.END;
                }
                board.setTile(row, col,
                        tileFactory.createTile("Tile" + row + col, type, new Point(row, col), "#808080"));
            }
        }
    }
}