package com.coffeeisoxigen.model.board.strategy;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.tile.TileFactory;

public interface TileLayoutStrategy {
    void placeTiles(Board board, TileFactory tileFactory);
}