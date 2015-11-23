package com.mygdx.event;

import com.mygdx.util.RelativeStopWatch;
import com.mygdx.world.World;

public abstract class Event implements IEvent {
    protected World world;
    private boolean isHappening;
    private boolean isOver;

    protected RelativeStopWatch stopWatch;

    public Event(World world) {
        this.world = world;
        this.isHappening = false;
        this.isOver = false;
        stopWatch = new RelativeStopWatch();
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
            stopWatch.update(delta);
            update(delta);
            if (mustEnd()) {
                end();
            }
        }
    }

    @Override
    public final void end() {
        if (!isOver) {
            terminate();
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

    protected void terminate() {
        // Does nothing by default
    }

    protected boolean mustEnd() {
        return false; // By default an event never ends
    }
}
