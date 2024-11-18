package com.coffeeisoxigen.model.board;

import java.io.File;
import java.io.IOException;

public interface IMapCreatable {
    void createMap(int width, int height, String name, String color);

    Board loadMap(File file) throws IOException;
}