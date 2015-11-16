package com.mygdx.entity;

import com.mygdx.world.Grid;

public abstract class Entity implements IEntity {
    private Grid grid;
    private Position position;

    public Entity(Grid grid) {
        this.grid = grid;
        this.position = new Position(0, 0);
    }

    @Override
    public void update(float delta) {
        // Does nothing
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.setPosition(x, y);
    }
}
