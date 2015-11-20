package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.util.Direction;
import com.mygdx.util.StopWatch;
import com.mygdx.world.Grid;

public class Arrow extends Entity {
    private static final long DISPLAYED = 300;
    private static final long HIDDEN = 100;

    private StopWatch stopWatch;
    private boolean displayed;

    public Arrow(Grid grid, Direction orientation) {
        super(grid, EntityInfo.ARROW);
        displayed = false;
        this.stopWatch = new StopWatch();
        stopWatch.start(HIDDEN);
        sprite.setColor(Color.RED);
        switch (orientation) {
            case LEFT:
                sprite.flip(true, false);
                break;
            case UP:
                sprite.rotate90(false);
                break;
            case DOWN:
                sprite.rotate90(true);
                break;
        }
    }

    @Override
    public void draw(Batch batch) {
        if (displayed) {
            super.draw(batch);
        }

        if (!displayed && stopWatch.getMilliseconds() > HIDDEN) {
            stopWatch.restart();
            displayed = true;
        } else if (stopWatch.getMilliseconds() > DISPLAYED){
            stopWatch.restart();
            displayed = false;
        }
    }
}
