package com.mygdx.event;

import com.mygdx.world.World;

public abstract class Event implements IEvent {
    protected World world;
    private boolean isHappening;
    private boolean isOver;

    public Event(World world) {
        this.world = world;
        this.isHappening = false;
        this.isOver = false;
    }

    @Override
    public final void start() {
        if (!isOver) {
            this.isHappening = true;
            world.getEvents().add(this);
            run();
        }
    }

    @Override
    public final void process(float delta) {
        if (isHappening && !isOver) {
            update(delta);
        }
    }

    @Override
    public final void end() {
        if (!isOver) {
            clean();
            this.isHappening = false;
            this.isOver = true;
        }
    }

    @Override
    public final boolean isHappening() {
        return isHappening;
    }

    @Override
    public boolean isOver() {
        return isOver;
    }


    /** Methods to override in subclasses to define an event **/
    protected void run() {
        // Does nothing by default
    }

    protected void update(float delta) {
        // Does nothing by default
    }

    protected void clean() {
        // Does nothing by default
    }
}
