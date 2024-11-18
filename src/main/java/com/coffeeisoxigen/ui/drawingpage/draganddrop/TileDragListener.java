package com.coffeeisoxigen.ui.drawingpage.draganddrop;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.coffeeisoxigen.utils.Point;

public class TileDragListener implements DragGestureListener, DragSourceListener, MouseListener, MouseMotionListener {
    private final DragSource dragSource;
    private Point coordinates;

    public TileDragListener(Point coordinates) {
        this.dragSource = new DragSource();
        this.coordinates = coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent event) {
        Transferable transferable = new TileTransferable(coordinates);
        dragSource.startDrag(event, DragSource.DefaultMoveDrop, transferable, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent event) {
        System.out.println("Drag entered at " + coordinates);
    }

    @Override
    public void dragOver(DragSourceDragEvent event) {
        System.out.println("Dragging over " + coordinates);
    }

    @Override
    public void dragExit(DragSourceEvent event) {
        System.out.println("Drag exited.");
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent event) {
        System.out.println("Drag ended.");
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent event) {
    }

    // MouseListener Methods
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed at " + coordinates);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // MouseMotionListener Methods
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragging at " + coordinates);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
