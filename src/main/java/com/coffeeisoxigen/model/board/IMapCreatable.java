package com.coffeeisoxigen.model.board;

import java.io.File;
import java.io.IOException;

public interface IMapCreatable {
    void createNewMap(String name, int width, int height, boolean isProtected); // Create a new map
    void loadSavedMap(File file) throws IOException; // Load a map from a file
    void createDefaultMap(); // Create a default map
    void createCustomMap(String name, int width, int height, boolean isProtected); // Create a custom map
    void updateCurrentMap(int width, int height); // Update the current map size
    void resetMap(); // Reset the map and clear all tiles
    void randomizeTiles(); // Randomize the tiles on the map
    void resetMapTiles(); // Reset the tiles without changing the size or the total data
    int getTotalTiles(); // Get the total number of tiles
}