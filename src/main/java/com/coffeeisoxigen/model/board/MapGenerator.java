package com.coffeeisoxigen.model.board;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.coffeeisoxigen.utils.Point;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapGenerator implements IMapCreatable {
    private Board board;

    @Override
    public void createNewMap(String name, int width, int height, boolean isProtected) {
        board = new Board(name, width, height, isProtected);
        initializeDefaultTiles();
    }

    @Override
    public void loadSavedMap(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BoardData boardData = mapper.readValue(file, BoardData.class);
        board = new Board(boardData.getMapName(), boardData.getMapRows(), boardData.getMapCols(),
                boardData.isProtected());
        for (TileData tileData : boardData.getTiles()) {
            Tile tile = new Tile(tileData.getName(), ETileType.valueOf(tileData.getType()),
                    new Point(tileData.getX(), tileData.getY()), tileData.getColor(), tileData.getImage(),
                    tileData.getIndex());
            board.setTile(tileData.getX(), tileData.getY(), tile);
        }
    }

    @Override
    public void createDefaultMap() {
        board = new Board("DefaultMap", 10, 10, false);
        initializeDefaultTiles();
    }

    @Override
    public void createCustomMap(String name, int width, int height, boolean isProtected) {
        board = new Board(name, width, height, isProtected);
        initializeDefaultTiles();
    }

    @Override
    public void updateCurrentMap(int width, int height) {
        board = new Board(board.getName(), width, height, board.isProtected());
        initializeDefaultTiles();
    }

    @Override
    public void resetMap() {
        board = new Board(board.getName(), board.getWidth(), board.getHeight(), board.isProtected());
        initializeDefaultTiles();
    }

    @Override
    public void randomizeTiles() {
        initializeTiles(true);
    }

    @Override
    public void resetMapTiles() {
        initializeTiles(false);
    }

    @Override
    public int getTotalTiles() {
        return board.getWidth() * board.getHeight();
    }

    private void initializeDefaultTiles() {
        initializeTiles(false);
    }

    private void initializeTiles(boolean randomize) {
        Random random = new Random();
        int index = 0;
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                ETileType type = ETileType.NORMAL;
                if (x == 0 && y == 0) {
                    type = ETileType.START;
                } else if (x == board.getWidth() - 1 && y == board.getHeight() - 1) {
                    type = ETileType.END;
                } else if (randomize) {
                    int chance = random.nextInt(10);
                    if (chance < 2) {
                        type = ETileType.DANGER;
                    } else if (chance < 4) {
                        type = ETileType.SAFE;
                    }
                }
                board.setTile(x, y, new Tile("Tile" + x + y, type, new Point(x, y), "#808080", "image.png", index++));
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}