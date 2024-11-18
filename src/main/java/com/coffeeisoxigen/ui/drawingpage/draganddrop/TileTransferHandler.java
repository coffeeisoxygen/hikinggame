package com.coffeeisoxigen.ui.drawingpage.draganddrop;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.Tile;
import com.coffeeisoxigen.utils.Point;

public class TileTransferHandler extends TransferHandler {
  private final Board board;
  private Point coordinates;

  public TileTransferHandler(Board board) {
    this.board = board;
  }

  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  protected Transferable createTransferable(JComponent c) {
    return new TileTransferable(coordinates);
  }

  @Override
  public int getSourceActions(JComponent c) {
    return MOVE;
  }

  @Override
  public boolean canImport(TransferSupport support) {
    return support.isDataFlavorSupported(TileTransferable.TILE_POINT_FLAVOR);
  }

  @Override
  public boolean importData(TransferSupport support) {
    if (!canImport(support)) {
      return false;
    }

    try {
      Transferable transferable = support.getTransferable();
      Point sourcePoint = (Point) transferable.getTransferData(TileTransferable.TILE_POINT_FLAVOR);

      Point targetPoint = coordinates; // Current drop location

      if (board.isInMap(targetPoint)) {
        Tile sourceTile = board.getTile(sourcePoint.getPosX(), sourcePoint.getPosY());
        Tile targetTile = board.getTile(targetPoint.getPosX(), targetPoint.getPosY());

        // Swap tiles in the board
        board.setTile(targetPoint.getPosX(), targetPoint.getPosY(), sourceTile);
        board.setTile(sourcePoint.getPosX(), sourcePoint.getPosY(), targetTile);

        // Update positions inside the Tile objects
        sourceTile.setPosition(targetPoint);
        targetTile.setPosition(sourcePoint);

        return true;
      }
    } catch (UnsupportedFlavorException | IOException ex) {
    }
    return false;
  }
}
