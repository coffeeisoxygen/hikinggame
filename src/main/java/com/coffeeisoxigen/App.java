package com.coffeeisoxigen;

import javax.swing.SwingUtilities;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.BoardFactory;
import com.coffeeisoxigen.ui.BoardUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Board board = BoardFactory.createBoard(1, 1);
            BoardUI BoardUI = new BoardUI(board);
        });
    }
}