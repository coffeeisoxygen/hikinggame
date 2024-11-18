package com.coffeeisoxigen.model.board;

import java.util.List;

import com.coffeeisoxigen.model.tile.TileData;

public class MapData {
    private String mapName;
    private int mapRows;
    private int mapCols;
    private List<TileData> tiles;

    // Getters and setters
    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getMapRows() {
        return mapRows;
    }

    public void setMapRows(int mapRows) {
        this.mapRows = mapRows;
    }

    public int getMapCols() {
        return mapCols;
    }

    public void setMapCols(int mapCols) {
        this.mapCols = mapCols;
    }

    public List<TileData> getTiles() {
        return tiles;
    }

    public void setTiles(List<TileData> tiles) {
        this.tiles = tiles;
    }
}