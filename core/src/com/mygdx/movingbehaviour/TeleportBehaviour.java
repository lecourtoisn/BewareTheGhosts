package com.mygdx.movingbehaviour;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;
import com.mygdx.entity.Position;
import com.mygdx.util.Direction;

public class TeleportBehaviour extends MovingBehaviour {

    public TeleportBehaviour(Entity entity) {
        super(entity);
    }

    @Override
    protected Position getNextPosition(float delta) {
        Vector2 unitVector = movement.getUnitVector();
        Position newPos = new Position(entity.getPosition());
        newPos.move(unitVector.x, unitVector.y, entity.getGrid());

        return newPos;
    }

    @Override
    protected void afterMovement() {
        movement = Direction.NONE;
    }
}
