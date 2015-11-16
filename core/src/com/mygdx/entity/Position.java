package com.mygdx.entity;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.world.Grid;

public class Position {
    private Vector2 rawPosition;

    public Position(float x, float y) {
        rawPosition = new Vector2(x, y);
    }

    public void move(float x, float y) {
        rawPosition.add(x, y);
    }

    public void move(float x, float y, Grid grid) {
        Vector2 gridCellUnit = grid.getCellUnit();
        rawPosition.add(x*gridCellUnit.x, y*gridCellUnit.y);
    }

    public Vector2 getPosition() {
        return rawPosition;
    }

    public void setPosition(float x, float y) {
        rawPosition.x = x;
        rawPosition.y = y;
    }
}
