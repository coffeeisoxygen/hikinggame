package com.coffeeisoxigen.ui.drawingpage.draganddrop;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.utils.Point;
import javax.swing.*;

import com.coffeeisoxigen.ui.drawingpage.DrawPageCenterPanel;
import com.coffeeisoxigen.ui.drawingpage.*;

public class TileDragAndDropHandler {
    private Board board;

    public TileDragAndDropHandler(Board board) {
        this.board = board;
    }

    public void enableDragAndDrop(DrawPageCenterPanel centerPanel) {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                JPanel tilePanel = centerPanel.getTilePanel(x, y); // Get panel
                if (tilePanel == null)
                    continue;

                // Add TransferHandler and DragListener
                TileTransferHandler transferHandler = new TileTransferHandler(board);
                transferHandler.setCoordinates(new Point(x, y));
                tilePanel.setTransferHandler(transferHandler);

                TileDragListener dragListener = new TileDragListener(new Point(x, y));
                tilePanel.addMouseMotionListener(dragListener);
            }
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
