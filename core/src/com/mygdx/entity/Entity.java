package com.mygdx.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.world.Grid;

public abstract class Entity implements IEntity {
    private Grid grid;
    private Position position;
    private Vector2 hitboxSize;

    public Entity(Grid grid, Vector2 hitboxSize) {
        this.grid = grid;
        this.position = new Position(0, 0);
        this.hitboxSize = hitboxSize;
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

    @Override
    public Rectangle getHitbox() {
        Rectangle hitbox = new Rectangle();
        hitbox.setCenter(position.getPosition());
        hitbox.setSize(hitboxSize.x, hitboxSize.y);
        return hitbox;
    }
}
