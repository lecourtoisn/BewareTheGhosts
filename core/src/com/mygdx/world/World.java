package com.mygdx.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.entity.Garry;
import com.mygdx.entity.IEntity;
import com.mygdx.event.IEvent;
import com.mygdx.event.GhostSalvo;

import java.util.HashSet;
import java.util.Set;

public class World {
    public final float WIDTH = 170;
    public final float HEIGHT = 100;
    private final float GRIDSIZE = 80;

    private Background background;
    private Grid grid;
    private Garry garry;
    private Set<IEntity> entities;

    //private IEvent ghostPopperEvent;
    private IEvent ghostAttack;

    public World() {
        this.background = new Background(WIDTH, HEIGHT);
        this.grid = new Grid(GRIDSIZE, WIDTH, HEIGHT);
        this.garry = new Garry(grid);

        this.entities = new HashSet<IEntity>();
        this.ghostAttack = new GhostSalvo(this, 3, true, 1500, 4, 3000);
    }

    public void update(float delta) {
        if (!ghostAttack.isHappening()) {
            ghostAttack.start();
        }

        garry.update(delta);
        ghostAttack.process(delta);
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

    public void addEntity(IEntity newGhost) {
        entities.add(newGhost);
    }

    public void clearEntities() {
        entities.clear();
    }

    public Set<IEntity> getEntities() {
        return entities;
    }
}
