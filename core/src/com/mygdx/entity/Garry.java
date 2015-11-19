package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.movingbehaviour.IMovingBehaviour;
import com.mygdx.movingbehaviour.TeleportBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.world.Grid;

public class Garry extends Entity implements IMovingBehaviour {
    private static final Texture TEXTURE = new Texture("garry.png");
    private static final float WIDTH = 6.2f;
    private static final float HEIGHT = 9;
    private static final Vector2 CENTER = new Vector2(WIDTH/2, HEIGHT/2);

    private IMovingBehaviour movingStrategy;

    public Garry(Grid grid) {
        super(grid, TEXTURE, new Vector2(WIDTH, HEIGHT), CENTER);
        setHitboxSize(WIDTH, HEIGHT);
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
