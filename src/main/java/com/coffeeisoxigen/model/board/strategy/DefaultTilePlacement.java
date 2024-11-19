package com.coffeeisoxigen.model.board.strategy;

import java.util.Arrays;
import java.util.List;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.TileFactory;
import com.coffeeisoxigen.utils.Point;

public class DefaultTilePlacement implements TileLayoutStrategy {
    private final List<Integer> dangerTiles;
    private final List<Integer> safeTiles;

    public DefaultTilePlacement() {
        this.dangerTiles = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 13, 17, 18, 20, 22, 25, 26, 27, 34, 41, 44, 45, 46, 50,
                53, 55, 56, 57, 58, 62, 65);
        this.safeTiles = Arrays.asList(19, 29, 37, 54, 60, 63);
    }

    @Override
    public void placeTiles(Board board, TileFactory tileFactory) {
        int rows = board.getWidth();
        int cols = board.getHeight();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Point point = new Point(row, col);
                ETileType type = ETileType.SAFE;

                if (row == 0 && col == 0) {
                    type = ETileType.START;
                } else if (row == rows - 1 && col == cols - 1) {
                    type = ETileType.END;
                } else if (dangerTiles.contains(row * cols + col)) {
                    type = ETileType.DANGER;
                } else if (safeTiles.contains(row * cols + col)) {
                    type = ETileType.SAFE;
                }

                board.setTile(row, col, tileFactory.createTile("Tile" + row + col, type, point, "#808080"));
            }
        }
    }
}