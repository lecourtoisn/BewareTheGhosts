package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.movingbehaviour.TeleportBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.world.Grid;

public class Garry extends Entity implements MovingBehaviour {
    public static final Texture TEXTURE = new Texture("garry.png");
    public static final float WIDTH = 6.43f;
    public static final float HEIGHT = 9;
    private MovingBehaviour movingStrategy;

    public Garry(Grid grid) {
        super(grid);
        getPosition().setPosition(grid.FIRSTCELL.x, grid.FIRSTCELL.y);
        movingStrategy = new TeleportBehaviour(this);
    }

    @Override
    public void update(float delta) {
        move(delta);
    }

    @Override
    public void draw(Batch batch) {
        Vector2 position = getPosition().getPosition();
        batch.draw(TEXTURE, position.x, position.y, WIDTH, HEIGHT);
    }


    /** Strategy Pattern **/
    @Override
    public void setMovingDirection(Direction direction) {
        movingStrategy.setMovingDirection(direction);
    }

    @Override
    public void move(float delta) {
        movingStrategy.move(delta);
    }
}
