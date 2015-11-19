package com.mygdx.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.entity.Arrow;
import com.mygdx.entity.Garry;
import com.mygdx.entity.Ghost;
import com.mygdx.entity.IEntity;
import com.mygdx.event.GhostSalvo;
import com.mygdx.event.IEvent;

public class World {
    public final float WIDTH = 170;
    public final float HEIGHT = 100;
    private final float GRIDSIZE = 80;

    private Background background;
    private Grid grid;
    private Garry garry;
    //private Set<IEntity> entities;
    EntityHolder entities;

    //private IEvent ghostPopperEvent;
    private IEvent ghostAttack;

    public World() {
        this.background = new Background(WIDTH, HEIGHT);
        this.grid = new Grid(GRIDSIZE, WIDTH, HEIGHT);
        this.garry = new Garry(grid);

        this.entities = new EntityHolder();
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

    public void render(Batch batch) {
        background.draw(batch);
        grid.draw(batch);
        garry.draw(batch);

        for (IEntity entity : entities.getEntities(Arrow.class)) {
            entity.draw(batch);
        }
        for (IEntity entity : entities.getEntities(Ghost.class)) {
            entity.draw(batch);
        }
    }

    public Garry getGarry() {
        return garry;
    }

    public Grid getGrid() {
        return grid;
    }

    public EntityHolder getEntities() {
        return entities;
    }
}
