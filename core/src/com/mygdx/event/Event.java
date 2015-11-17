package com.mygdx.event;

import com.mygdx.world.World;

public abstract class Event implements IEvent {
    protected World world;
    private boolean isHappening;
    public Event(World world) {
        this.world = world;
        this.isHappening = false;
    }

    @Override
    public final void start() {
        this.isHappening = true;
        initialize();
    }

    @Override
    public final void process(float delta) {
        if (isHappening) {
            update(delta);
        }
    }

    @Override
    public final void end() {
        this.isHappening = false;
        clean();
    }

    @Override
    public final boolean isHappening() {
        return false;
    }

    protected void initialize() {
        // Does nothing by default
    }

    protected abstract void update(float delta);

    protected void clean() {
        // Does nothing by default
    }
}
