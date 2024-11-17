package com.coffeeisoxigen.model.tile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.coffeeisoxigen.utils.Point;

public class Tile {
    private String name;
    private ETileType tileType;
    private Point position;
    private String color;
    private String image;
    private PropertyChangeSupport support;

    public Tile(String name, ETileType tileType, Point position, String color, String image) {
        this.name = (name == null || name.isEmpty()) ? "unnamed" : name;
        this.tileType = tileType;
        this.position = position;
        this.color = color;
        this.image = image;
        this.support = new PropertyChangeSupport(this);
    }

    public String getName() {
        return name;
    }

    public ETileType getTileType() {
        return tileType;
    }

    public Point getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String oldColor = this.color;
        this.color = color;
        support.firePropertyChange("color", oldColor, color);
    }

    public String getImage() {
        return image;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}