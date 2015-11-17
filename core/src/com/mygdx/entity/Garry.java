package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.movingbehaviour.MovingBehaviour;
import com.mygdx.movingbehaviour.TeleportBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.world.Grid;

public class Garry extends Entity implements MovingBehaviour {
    private static final Texture TEXTURE = new Texture("garry.png");
    private static final float WIDTH = 6.2f;
    private static final float HEIGHT = 9;
    private MovingBehaviour movingStrategy;
    private Vector2 center;

    public Garry(Grid grid) {
        super(grid, new Vector2(WIDTH, HEIGHT));
        getPosition().setPosition(grid.getCellCenterPosition(0, 0));
        movingStrategy = new TeleportBehaviour(this);
        this.center = new Vector2(WIDTH/2, HEIGHT/2);
    }

    @Override
    public void update(float delta) {
        move(delta);
    }

    @Override
    public void draw(Batch batch) {
        Vector2 position = new Vector2(getPosition().getPosition());
        position.sub(center);
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
