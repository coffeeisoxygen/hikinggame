package com.coffeeisoxigen.model.board;

import java.io.File;
import java.io.IOException;

public interface IMapCreatable {
    void createMap(String name, int width, int height, boolean isProtected);

    Board loadMap(File file) throws IOException;
}