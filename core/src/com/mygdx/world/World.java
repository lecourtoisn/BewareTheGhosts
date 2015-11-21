package com.mygdx.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.mygdx.entity.Arrow;
import com.mygdx.entity.Garry;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.IEntity;
import com.mygdx.event.GhostSalvo;
import com.mygdx.event.IEvent;
import com.mygdx.util.GenericHolder;

public class World {
//    public final float WIDTH = 170;
    public final float HEIGHT = 100;
    public final float WIDTH = Gdx.graphics.getWidth() * 100f / Gdx.graphics.getHeight();
    private final float GRIDSIZE = 80;

    private Background background;
    private Grid grid;
    private Garry garry;
    private GenericHolder<IEntity> entities;

    private IEvent ghostAttack;

    public World() {
        this.background = new Background(WIDTH, HEIGHT);
        this.grid = new Grid(GRIDSIZE, WIDTH, HEIGHT);
        this.garry = new Garry(grid);

        this.entities = new GenericHolder<IEntity>();
        this.ghostAttack = new GhostSalvo(this, 3, false, 1000, 20, 1000);
    }

    public void update(float delta) {
        if (!ghostAttack.isHappening()) {
            ghostAttack.start();
        }

        garry.update(delta);
        ghostAttack.process(delta);
        for (IEntity entity : entities.getEntities()) {
            entity.update(delta);
        }
    }

    public void render(Batch batch, OrthographicCamera cam) {
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = grid.getBoundaries();
        ScissorStack.calculateScissors(cam, batch.getTransformMatrix(), clipBounds, scissors);

        background.draw(batch);
        grid.draw(batch);
        garry.draw(batch);

        for (IEntity entity : entities.getEntities(Arrow.class)) {
            entity.draw(batch);
        }

        batch.flush();
        ScissorStack.pushScissors(scissors);
        for (IEntity entity : entities.getEntities(Enemy.class)) {
            entity.draw(batch);
        }
        batch.flush();
        ScissorStack.popScissors();
    }

    public Garry getGarry() {
        return garry;
    }

    public Grid getGrid() {
        return grid;
    }

    public GenericHolder getEntities() {
        return entities;
    }
}
