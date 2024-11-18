package com.coffeeisoxigen.model.board;

import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;

public class MapGeneratorDefault implements IMapGenerator {

    @Override
    public Board generateMap(int width, int height) {
        Board board = new Board(width, height);
        initializeNormalTiles(board, width, height);
        return board;
    }

    private void initializeNormalTiles(Board board, int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board.setTile(x, y, new Tile("Tile" + x + y, ETileType.NORMAL, new Point(x, y),
                        ETileType.NORMAL.getColor(), "image.png"));
            }
        }
    }

    @Override
    public int getDefaultWidth() {
        return 1;
    }

    @Override
    public int getDefaultHeight() {
        return 1;
    }
}