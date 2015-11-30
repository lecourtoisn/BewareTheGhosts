package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.movingbehaviour.GlidingBehaviour;
import com.mygdx.movingbehaviour.IMovingBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.world.World;

public class Enemy extends WorldEntity implements IMovingBehaviour{
    private static final float VELOCITY = 37.5f;

    private IMovingBehaviour movingBehaviour;

    public Enemy(World world, EntityInfo entInfo) {
        super(world, entInfo);
        movingBehaviour = new GlidingBehaviour(this, VELOCITY);

        setPosition(getGrid().getCellCenterPosition(1, 1));

        sprite.setColor(Color.CHARTREUSE);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        move(delta);
    }

    @Override
    public void whenCollidesWithGarry(Garry garry) {
        garry.getsHurt();
    }

    /** Private & protected **

    /** Strategy pattern **/
    @Override
    public void setMovingDirection(Direction direction) {
        movingBehaviour.setMovingDirection(direction);
        if (direction.equals(Direction.RIGHT)) {
            sprite.flip(true, false);
        }
    }

    @Override
    public void move(float delta) {
        movingBehaviour.move(delta);
    }
}
