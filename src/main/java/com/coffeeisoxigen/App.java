package com.coffeeisoxigen;

import javax.swing.SwingUtilities;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.MapGeneratorDefault;
import com.coffeeisoxigen.ui.BoardUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MapGeneratorDefault mapGenerator = new MapGeneratorDefault();
            Board board = mapGenerator.generateMap(mapGenerator.getDefaultWidth(), mapGenerator.getDefaultHeight());
            BoardUI boardUI = new BoardUI(board);
            boardUI.setVisible(true);
        });
    }
}