package com.coffeeisoxigen.model.board;

import java.io.File;
import java.io.IOException;

import com.coffeeisoxigen.model.board.strategy.CustomTilePlacement;
import com.coffeeisoxigen.model.board.strategy.DefaultTilePlacement;
import com.coffeeisoxigen.model.board.strategy.TileLayoutStrategy;
import com.coffeeisoxigen.model.tile.TileFactory;

public class MapGenerator implements IMapGenerator {
    private Board board;
    private TileFactory tileFactory;

    public MapGenerator() {
        this.tileFactory = new TileFactory();
    }

    @Override
    public void createMap() {
        board = new Board("DefaultMap", 12, 6, true);
        TileLayoutStrategy tileLayoutStrategy = new DefaultTilePlacement();
        tileLayoutStrategy.placeTiles(board, tileFactory);
    }

    @Override
    public void createMap(String name, int width, int height) {
        board = new Board(name, width, height, false);
        TileLayoutStrategy tileLayoutStrategy = new CustomTilePlacement();
        tileLayoutStrategy.placeTiles(board, tileFactory);
    }

    @Override
    public void resetMap() {
        TileLayoutStrategy tileLayoutStrategy = new DefaultTilePlacement();
        tileLayoutStrategy.placeTiles(board, tileFactory);
    }

    @Override
    public void resetMap(String name, int width, int height) {
        board = new Board(name, width, height, false);
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

    @Override
    public void loadSavedMap(File file) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadSavedMap'");
    }
}