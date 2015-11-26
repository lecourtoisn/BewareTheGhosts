package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.util.Direction;
import com.mygdx.util.RelativeStopWatch;
import com.mygdx.world.World;

public class Arrow extends WorldEntity {
    private static final long DISPLAYED = 300;
    private static final long HIDDEN = 100;

    //private StopWatch stopWatch;
    private RelativeStopWatch stopWatch;
    private boolean displayed;

    public Arrow(World world, Direction orientation) {
        super(world, EntityInfo.ARROW);
        displayed = false;
        //this.stopWatch = new StopWatch();
        //stopWatch.start(HIDDEN);
        this.stopWatch = new RelativeStopWatch();
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
    public void update(float delta) {
        stopWatch.update(delta);
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
