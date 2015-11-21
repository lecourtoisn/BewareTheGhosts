package com.mygdx.entity;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.movingbehaviour.IMovingBehaviour;
import com.mygdx.movingbehaviour.TeleportBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.util.Position;
import com.mygdx.world.Grid;

public class Garry extends Entity implements IMovingBehaviour {

    private IMovingBehaviour movingStrategy;

    public Garry(Grid grid) {
        super(grid, EntityInfo.GARRY);
        setHitboxSize(entityInfo.getSize().x, entityInfo.getSize().y);
        movingStrategy = new TeleportBehaviour(this);

        setPosition(grid.getCellCenterPosition(0, 0));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        move(delta);
    }

    /** Private & protected **/
    @Override
    public boolean couldBeAt(Position pos) {
        Rectangle gridHitbox = getGrid().getBoundaries();
        Rectangle entityHitbox = getHitbox();
        entityHitbox.setCenter(pos.getPosition());

        return gridHitbox.contains(entityHitbox);
    }

    /** Strategy pattern **/
    @Override
    public void setMovingDirection(Direction direction) {
        movingStrategy.setMovingDirection(direction);
    }

    @Override
    public void move(float delta) {
        movingStrategy.move(delta);
    }
}
