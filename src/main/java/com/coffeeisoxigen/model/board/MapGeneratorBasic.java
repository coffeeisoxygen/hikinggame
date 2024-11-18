package com.coffeeisoxigen.model.board;

import java.util.Arrays;
import java.util.List;

import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.utils.Point;

public class MapGeneratorBasic implements IMapGenerator {
    private final List<Integer> dangerTiles;
    private final List<Integer> safeTiles;

    public MapGeneratorBasic() {
        // Define danger and safe tiles
        this.dangerTiles = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        this.safeTiles = Arrays.asList(19, 29, 37, 54, 60, 63);
    }

    @Override
    public Board generateMap(int width, int height) {
        Board board = new Board(width, height);
        initializeNormalTiles(board, width, height);
        setSpecialTiles(board, dangerTiles, ETileType.DANGER, width);
        setSpecialTiles(board, safeTiles, ETileType.SAFE, width);
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

    private void setSpecialTiles(Board board, List<Integer> tileIndices, ETileType tileType, int width) {
        int totalTiles = board.getWidth() * board.getHeight();
        for (int tile : tileIndices) {
            if (tile < totalTiles) {
                Point point = convertToPoint(tile, width);
                if (point.getPosX() < board.getWidth() && point.getPosY() < board.getHeight()) {
                    board.setTile(point.getPosX(), point.getPosY(), new Tile("Tile" + point.getPosX() + point.getPosY(),
                            tileType, point, tileType.getColor(), "image.png"));
                }
            }
        }
    }

    private Point convertToPoint(int tile, int width) {
        int x = tile / width;
        int y = tile % width;
        return new Point(x, y);
    }

    @Override
    public int getDefaultWidth() {
        return 6;
    }

    @Override
    public int getDefaultHeight() {
        return 12;
    }
}