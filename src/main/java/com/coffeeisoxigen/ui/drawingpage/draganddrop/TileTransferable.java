package com.coffeeisoxigen.ui.drawingpage.draganddrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.coffeeisoxigen.utils.Point;

public class TileTransferable implements Transferable {
  public static final DataFlavor TILE_POINT_FLAVOR = new DataFlavor(Point.class, "Point");
  private final Point point;

  public TileTransferable(Point point) {
    this.point = point;
  }

  @Override
  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[] { TILE_POINT_FLAVOR };
  }

  @Override
  public boolean isDataFlavorSupported(DataFlavor flavor) {
    return TILE_POINT_FLAVOR.equals(flavor);
  }

  @Override
  public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
    if (!isDataFlavorSupported(flavor)) {
      throw new UnsupportedFlavorException(flavor);
    }
    return point;
  }
}
