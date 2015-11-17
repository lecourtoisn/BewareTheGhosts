package com.mygdx.movingbehaviour;

import com.mygdx.entity.Entity;
import com.mygdx.entity.Position;
import com.mygdx.util.Direction;

public abstract class MovingBehaviour implements IMovingBehaviour{
    protected Entity entity;
    protected Direction movement;

    public MovingBehaviour(Entity entity) {
        this.entity = entity;
        movement = Direction.NONE;
    }

    @Override
    public void setMovingDirection(Direction direction) {
        this.movement = direction;
    }

    @Override
    public void move(float delta) {
        Position nextPos = getNextPosition(delta);
        if (entity.couldBeAt(nextPos)) {
            entity.setPosition(nextPos);
        }
        afterMovement();
    }

    protected void afterMovement() {
        // Doesn't do anything by default
    }

    protected abstract Position getNextPosition(float delta);
}
