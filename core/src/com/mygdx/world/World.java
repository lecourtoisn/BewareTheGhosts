package com.mygdx.world;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.mygdx.entity.Arrow;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.Garry;
import com.mygdx.entity.IEntity;
import com.mygdx.event.IEvent;
import com.mygdx.util.GenericHolder;

import java.util.HashSet;
import java.util.Set;

public class World {
    //public static final float WIDTH = 100;
    //public static final float HEIGHT = 100;

    private Background background;
    private Grid grid;
    private Garry garry;

    private GenericHolder<IEntity> entities;
    private GenericHolder<IEvent> events;

    private float width, height;

    public World(float width, float height) {
        float GRIDSIZE = 80;
        this.width = width;
        this.height = height;
        this.background = new Background(width, height);
        this.grid = new Grid(GRIDSIZE, width, height);
        this.garry = new Garry(this);

        this.entities = new GenericHolder<IEntity>();
        this.events = new GenericHolder<IEvent>();

        entities.add(garry);
    }

    public void update(float delta) {
        for (IEvent event : events.getElements()) {
            event.process(delta);
        }

        for (IEntity entity : entities.getElements()) {
            entity.update(delta);
        }
    }

    public void render(Batch batch, Camera cam) {
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = grid.getBoundaries();
        ScissorStack.calculateScissors(cam, batch.getTransformMatrix(), clipBounds, scissors);

        background.draw(batch);
        grid.draw(batch);
        garry.draw(batch);

        for (IEntity entity : entities.getElements(Arrow.class)) {
            entity.draw(batch);
        }

        batch.flush();
        ScissorStack.pushScissors(scissors);
        for (IEntity entity : entities.getElements(Enemy.class)) {
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

    public GenericHolder<IEntity> getEntities() {
        return entities;
    }

    public GenericHolder<IEvent> getEvents() {
        return events;
    }

    public Set<IEntity> getCollisions(IEntity entity) {
        Set<IEntity> colliding = new HashSet<IEntity>();
        for (IEntity other : entities.getElements()) {
            Rectangle entityHitbox = entity.getHitbox();
            Rectangle otherHitbox = other.getHitbox();
            if (!entity.equals(other) && entityHitbox.overlaps(otherHitbox)) {
                colliding.add(other);
            }
        }
        return colliding;
    }
}
