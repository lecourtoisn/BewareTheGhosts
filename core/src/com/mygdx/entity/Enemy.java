package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.movingbehaviour.GlidingBehaviour;
import com.mygdx.movingbehaviour.IMovingBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.world.Grid;

public class Enemy extends Entity implements IMovingBehaviour{
    private static final float VELOCITY = 15;

    private IMovingBehaviour movingBehaviour;

    public Enemy(Grid grid, EntityInfo entInfo) {
        super(grid, entInfo);
        setHitboxSize(entityInfo.getSize().x, entityInfo.getSize().y);
        movingBehaviour = new GlidingBehaviour(this, VELOCITY);

        setPosition(grid.getCellCenterPosition(1, 1));

        sprite.setColor(Color.CHARTREUSE);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        move(delta);
    }

    /** Private & protected **

    /** Strategy pattern **/
    @Override
    public void setMovingDirection(Direction direction) {
        movingBehaviour.setMovingDirection(direction);
    }

    @Override
    public void move(float delta) {
        movingBehaviour.move(delta);
    }
}
