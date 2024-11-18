package com.coffeeisoxigen.model.board;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.model.tile.TileData;
import com.coffeeisoxigen.utils.Point;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapGenerator implements IMapCreatable {
    private Board board;

    @Override
    public void createMap(int width, int height, String name, String color) {
        board = new Board(width, height);
        Random random = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ETileType type = ETileType.NORMAL;
                if (x == 0 && y == 0) {
                    type = ETileType.END;
                } else if (x == width - 1 && y == height - 1) {
                    type = ETileType.START;
                } else if (random.nextInt(10) < 2) {
                    type = ETileType.DANGER;
                } else if (random.nextInt(10) < 2) {
                    type = ETileType.SAFE;
                }
                board.setTile(x, y, new Tile("Tile" + x + y, type, new Point(x, y), color, "image.png"));
            }
        }
    }

    @Override
    public Board loadMap(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MapData mapData = mapper.readValue(file, MapData.class);
        Board board = new Board(mapData.getMapRows(), mapData.getMapCols());
        for (TileData tileData : mapData.getTiles()) {
            Tile tile = new Tile(tileData.getName(), ETileType.valueOf(tileData.getType()),
                    new Point(tileData.getX(), tileData.getY()), tileData.getColor(), tileData.getImage());
            board.setTile(tileData.getX(), tileData.getY(), tile);
        }
        return board;
    }

    public Board getBoard() {
        return board;
    }
}