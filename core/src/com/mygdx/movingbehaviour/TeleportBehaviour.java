package com.mygdx.movingbehaviour;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.WorldEntity;
import com.mygdx.util.Direction;
import com.mygdx.util.Position;

public class TeleportBehaviour extends MovingBehaviour {

    public TeleportBehaviour(WorldEntity entity) {
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
