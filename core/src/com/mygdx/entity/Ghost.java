package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.movingbehaviour.GlidingBehaviour;
import com.mygdx.movingbehaviour.IMovingBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.world.Grid;

public class Ghost extends Entity implements IMovingBehaviour{
    private static final Texture TEXTURE = new Texture("ghost.png");
    private static final float WIDTH = 6.2f;
    private static final float HEIGHT = 9;
    private static final Vector2 CENTER = new Vector2(WIDTH/2, HEIGHT/2);
    private static final float VELOCITY = 15;

    private IMovingBehaviour movingBehaviour;

    public Ghost(Grid grid) {
        super(grid, TEXTURE, new Vector2(WIDTH, HEIGHT), CENTER);
        setHitboxSize(WIDTH, HEIGHT);
        movingBehaviour = new GlidingBehaviour(this, VELOCITY);

        setPosition(grid.getCellCenterPosition(1, 1));

        sprite.setColor(Color.LIME);
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
