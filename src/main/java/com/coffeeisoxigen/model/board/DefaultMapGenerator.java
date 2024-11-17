package com.coffeeisoxigen.model.board;

import java.util.Arrays;
import java.util.List;

import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;

public class DefaultMapGenerator implements IMapGenerator {
    private List<Integer> dangerTiles;
    private List<Integer> safeTiles;

    public DefaultMapGenerator() {
        // Define danger and safe tiles
        this.dangerTiles = Arrays.asList(1);
        this.safeTiles = Arrays.asList(19, 29, 37, 54, 60, 63);
    }

    @Override
    public Board generateMap(int width, int height) {
        Board board = new Board(width, height);
        int totalTiles = width * height;

        // Step 1: Set all tiles as NORMAL
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board.setTile(x, y, new Tile("Tile" + x + y, ETileType.NORMAL, new Point(x, y), ETileType.NORMAL.getColor(), "image.png"));
            }
        }

        // Step 2: Set danger tiles
        for (int tile : dangerTiles) {
            if (tile < totalTiles) {
                Point point = convertToPoint(tile, width);
                board.setTile(point.getX(), point.getY(), new Tile("Tile" + point.getX() + point.getY(), ETileType.DANGER, point, ETileType.DANGER.getColor(), "image.png"));
            }
        }

        // Step 3: Set safe tiles
        for (int tile : safeTiles) {
            if (tile < totalTiles) {
                Point point = convertToPoint(tile, width);
                board.setTile(point.getX(), point.getY(), new Tile("Tile" + point.getX() + point.getY(), ETileType.SAFE, point, ETileType.SAFE.getColor(), "image.png"));
            }
        }

        return board;
    }

    @Override
    public int getDefaultWidth() {
        return 6;
    }

    @Override
    public int getDefaultHeight() {
        return 12;
    }

    private Point convertToPoint(int tile, int width) {
        int x = tile % width;
        int y = tile / width;
        return new Point(x, y);
    }
}