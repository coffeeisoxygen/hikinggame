package com.coffeeisoxigen.model.board;

import java.io.File;
import java.io.IOException;

public interface IMapGenerator {
    void createMap(); // Create a default map

    void createMap(String name, int width, int height); // Create a custom map

    void loadSavedMap(File file) throws IOException; // Load a map from a file

    void resetMap(); // Reset the map to its default state

    void resetMap(String name, int width, int height); // Reset the map with specified parameters

    int getTotalTiles(); // Get the total number of tiles
}