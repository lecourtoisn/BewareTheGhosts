package com.mygdx.movingbehaviour;

import com.mygdx.entity.Entity;
import com.mygdx.entity.MovingBehaviour;
import com.mygdx.util.Direction;

public class TeleportBehaviour implements MovingBehaviour {
    private Entity entity;
    private Direction movement;

    public TeleportBehaviour(Entity entity) {
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
            float cellUnit = entity.getGrid().CELLUNIT;
            entity.getPosition().move(cellUnit, 0);
            movement = Direction.NONE;
        }
    }
}
