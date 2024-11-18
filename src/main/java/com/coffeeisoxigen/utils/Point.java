package com.coffeeisoxigen.utils;

import java.util.Objects;

public class Point {
    private int posX;
    private int posY;

    public Point(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void move(int dx, int dy) {
        this.posX += dx;
        this.posY += dy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point) o;
        return posX == point.posX && posY == point.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + posX + ", y=" + posY + '}';
    }
}