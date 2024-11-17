package com.coffeeisoxigen.model.board;

public interface IMapGenerator {
    Board generateMap(int width, int height);

    int getDefaultWidth();

    int getDefaultHeight();
}