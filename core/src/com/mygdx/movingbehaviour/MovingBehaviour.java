package com.mygdx.movingbehaviour;

import com.mygdx.entity.WorldEntity;
import com.mygdx.util.Direction;
import com.mygdx.util.Position;

public abstract class MovingBehaviour implements IMovingBehaviour{
    protected WorldEntity entity;
    protected Direction movement;

    public MovingBehaviour(WorldEntity entity) {
        this.entity = entity;
        movement = Direction.NONE;
    }

    @Override
    public void setMovingDirection(Direction direction) {
        this.movement = direction;
    }

    @Override
    public void move(float delta) {
        if (movement != Direction.NONE) {
            Position nextPos = getNextPosition(delta);
            if (entity.couldBeAt(nextPos)) {
                entity.setPosition(nextPos);
            }
            afterMovement();
        }
    }

    protected void afterMovement() {
        // Doesn't do anything by default
    }

    protected abstract Position getNextPosition(float delta);
}
