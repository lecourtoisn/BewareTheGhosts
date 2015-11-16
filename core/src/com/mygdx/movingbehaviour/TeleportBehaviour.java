package com.mygdx.movingbehaviour;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;
import com.mygdx.entity.Position;
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
            Vector2 unitVector = movement.getUnitVector();
            Position newPos = new Position(entity.getPosition());
            newPos.move(unitVector.x, unitVector.y, entity.getGrid());
            if (entity.getGrid().getBoundaries().contains(newPos.getPosition())) {
                entity.setPosition(newPos.getX(), newPos.getY());
            }
            movement = Direction.NONE;
        }
    }
}
