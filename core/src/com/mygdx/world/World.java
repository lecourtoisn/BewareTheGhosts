package com.mygdx.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.entity.Garry;
import com.mygdx.entity.Ghost;
import com.mygdx.entity.IEntity;
import com.mygdx.event.GhostPopperEvent;
import com.mygdx.event.IEvent;
import com.mygdx.util.StopWatch;

import java.util.HashSet;
import java.util.Set;

public class World {
    public final float WIDTH = 170;
    public final float HEIGHT = 100;
    private final float GRIDSIZE = 90;

    private Background background;
    private Grid grid;
    private Garry garry;
    private Set<IEntity> entities;

    private IEvent ghostPopperEvent;
    private StopWatch stopWatch = new StopWatch();
    private StopWatch stopWatch2 = new StopWatch();

    public World() {
        this.background = new Background(WIDTH, HEIGHT);
        this.grid = new Grid(GRIDSIZE, WIDTH, HEIGHT);
        this.garry = new Garry(grid);

        this.entities = new HashSet<IEntity>();
        this.ghostPopperEvent = new GhostPopperEvent(this);
        stopWatch.start();
    }

    public void update(float delta) {
        if (stopWatch.getSeconds() > 5) {
            ghostPopperEvent.start();
            stopWatch.stop();
            stopWatch2.start();
        }
        if (stopWatch2.getSeconds() > 5) {
            ghostPopperEvent.end();
            stopWatch2.stop();
            stopWatch.start();
        }


        garry.update(delta);
        ghostPopperEvent.process(delta);
        for (IEntity entity : entities) {
            entity.update(delta);
        }
    }

    public void render(Batch batch) {
        background.draw(batch);
        grid.draw(batch);
        garry.draw(batch);

        for (IEntity entity : entities) {
            entity.draw(batch);
        }
    }

    public Garry getGarry() {
        return garry;
    }

    public Grid getGrid() {
        return grid;
    }

    public void addEntity(Ghost newGhost) {
        entities.add(newGhost);
    }
}
