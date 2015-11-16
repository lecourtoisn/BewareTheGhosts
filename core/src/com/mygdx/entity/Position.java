package com.mygdx.entity;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.world.Grid;

public class Position {
    private Vector2 rawPosition;

    public Position(float x, float y) {
        rawPosition = new Vector2(x, y);
    }

    public Position(Position position) {
        rawPosition = new Vector2(position.getPosition());
    }

    public void move(float x, float y) {
        rawPosition.add(x, y);
    }

    public void move(float x, float y, Grid grid) {
        float cellUnit = grid.getCellUnit();
        rawPosition.add(x*cellUnit, y*cellUnit);
    }

    public Vector2 getPosition() {
        return rawPosition;
    }

    public void setPosition(float x, float y) {
        rawPosition.x = x;
        rawPosition.y = y;
    }

    public float getX() {
        return rawPosition.x;
    }

    public float getY() {
        return rawPosition.y;
    }

    public void setPosition(Position position) {
        getPosition().x = position.getX();
        getPosition().y = position.getY();
    }

    public void add(Position position) {
        rawPosition.add(position.getPosition());
    }

    @Override
    public String toString() {
        return rawPosition.toString();
    }
}
