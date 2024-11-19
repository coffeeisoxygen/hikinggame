package com.coffeeisoxigen.controller;

import java.io.File;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.MapGenerator;
import com.coffeeisoxigen.model.tile.ETileType;
import com.coffeeisoxigen.model.tile.Tile;

public class BoardController {
    private Board board;
    private MapGenerator mapGenerator;

    public BoardController(MapGenerator mapGenerator) {
        this.mapGenerator = mapGenerator;
    }

    public void createDefaultMap() {
        mapGenerator.createMap();
        board = mapGenerator.getBoard();
    }

    public void createCustomMap(String name, int width, int height) {
        mapGenerator.createMap(name, width, height);
        board = mapGenerator.getBoard();
    }

    public void resetMap() {
        mapGenerator.resetMap();
        board = mapGenerator.getBoard();
    }

    public void resetMap(String name, int width, int height) {
        mapGenerator.resetMap(name, width, height);
        board = mapGenerator.getBoard();
    }

    public int getTotalTiles() {
        return mapGenerator.getTotalTiles();
    }

    public void setTileType(int x, int y, ETileType tileType) {
        Tile tile = board.getTile(x, y);
        if (tile != null) {
            tile.setTileType(tileType);
        }
    }

    public ETileType getTileType(int x, int y) {
        Tile tile = board.getTile(x, y);
        return tile != null ? tile.getTileType() : null;
    }

    public void setTileColor(int x, int y, String color) {
        Tile tile = board.getTile(x, y);
        if (tile != null) {
            tile.setColor(color);
        }
    }

    public String getTileColor(int x, int y) {
        Tile tile = board.getTile(x, y);
        return tile != null ? tile.getColor() : null;
    }

    public void setTileName(int x, int y, String name) {
        Tile tile = board.getTile(x, y);
        if (tile != null) {
            tile.setName(name);
        }
    }

    public String getTileName(int x, int y) {
        Tile tile = board.getTile(x, y);
        return tile != null ? tile.getName() : null;
    }

    public Board getBoard() {
        return board;
    }

    public void loadSavedMap(File selectedFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadSavedMap'");
    }
}