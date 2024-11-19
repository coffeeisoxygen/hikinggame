package com.coffeeisoxigen.model.board;

import java.io.File;
import java.io.IOException;

import com.coffeeisoxigen.model.board.strategy.CustomTilePlacement;
import com.coffeeisoxigen.model.board.strategy.DefaultTilePlacement;
import com.coffeeisoxigen.model.board.strategy.TileLayoutStrategy;
import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.Tile;
import com.coffeeisoxigen.model.tile.TileData;
import com.coffeeisoxigen.model.tile.TileFactory;
import com.coffeeisoxigen.utils.Point;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapGenerator implements IMapGenerator {
    private Board board;
    private TileFactory tileFactory;

    public MapGenerator() {
        this.tileFactory = new TileFactory();
    }

    @Override
    public void createMap() {
        board = new Board("DefaultMap", 10, 10, false);
        TileLayoutStrategy tileLayoutStrategy = new DefaultTilePlacement();
        tileLayoutStrategy.placeTiles(board, tileFactory);
    }

    @Override
    public void createMap(String name, int width, int height, boolean isProtected) {
        board = new Board(name, width, height, isProtected);
        TileLayoutStrategy tileLayoutStrategy = new CustomTilePlacement();
        tileLayoutStrategy.placeTiles(board, tileFactory);
    }

    @Override
    public void loadSavedMap(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BoardData boardData = mapper.readValue(file, BoardData.class);
        board = new Board(boardData.getMapName(), boardData.getMapRows(), boardData.getMapCols(),
                boardData.isProtected());
        for (TileData tileData : boardData.getTiles()) {
            Tile tile = new Tile(tileData.getName(), ETileType.valueOf(tileData.getType()),
                    new Point(tileData.getX(), tileData.getY()), tileData.getColor());
            board.setTile(tileData.getX(), tileData.getY(), tile);
        }
    }

    @Override
    public void resetMap() {
        TileLayoutStrategy tileLayoutStrategy = new DefaultTilePlacement();
        tileLayoutStrategy.placeTiles(board, tileFactory);
    }

    @Override
    public void resetMap(String name, int width, int height, boolean isProtected) {
        board = new Board(name, width, height, isProtected);
        TileLayoutStrategy tileLayoutStrategy = new DefaultTilePlacement();
        tileLayoutStrategy.placeTiles(board, tileFactory);
    }

    @Override
    public int getTotalTiles() {
        return board.getWidth() * board.getHeight();
    }

    public Board getBoard() {
        return board;
    }
}