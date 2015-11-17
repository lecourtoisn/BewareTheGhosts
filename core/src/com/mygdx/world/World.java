package com.mygdx.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.entity.Garry;
import com.mygdx.entity.Ghost;
import com.mygdx.entity.IEntity;
import com.mygdx.util.Direction;

import java.util.List;

public class World {
    public final float WIDTH = 170;
    public final float HEIGHT = 100;
    private final float GRIDSIZE = 90;

    private Background background;
    private Grid grid;
    private Garry garry;
    private List<IEntity> entities;
    private Ghost TESTGHOST;

    public World() {
        this.background = new Background(WIDTH, HEIGHT);
        this.grid = new Grid(GRIDSIZE, WIDTH, HEIGHT);
        this.garry = new Garry(grid);
        this.TESTGHOST = new Ghost(grid);
        this.TESTGHOST.setMovingDirection(Direction.RIGHT);
    }

    public void update(float delta) {
        garry.update(delta);
        TESTGHOST.update(delta);
    }

    public void render(Batch batch) {
        background.draw(batch);
        grid.draw(batch);
        garry.draw(batch);
        TESTGHOST.draw(batch);
    }

    public Garry getGarry() {
        return garry;
    }
}
