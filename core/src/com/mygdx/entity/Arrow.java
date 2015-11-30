package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.util.Direction;
import com.mygdx.util.RelativeStopWatch;
import com.mygdx.world.World;

public class Arrow extends WorldEntity {
    private static final float DISPLAYED_RATIO = 1/2f;

    private RelativeStopWatch stopWatch;
    private boolean visible;
    private int nbBlink;
    private int blinkCounter;
    private long blinkCycleDuration;

    public Arrow(World world, Direction orientation, int nbBlink, long blinkCycleDuration) {
        super(world, EntityInfo.ARROW);
        visible = false;
        this.stopWatch = new RelativeStopWatch();
        this.nbBlink = nbBlink;
        this.blinkCycleDuration = blinkCycleDuration;
        this.blinkCounter = 0;
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
        if (!stopWatch.isRunning()) {
            stopWatch.start();
        }
        stopWatch.update(delta);

        long timeInCycle = stopWatch.getMilliseconds()%blinkCycleDuration;
        float visiblePeriod = DISPLAYED_RATIO * blinkCycleDuration;
        if (timeInCycle < visiblePeriod && !stoppedBlinking()) {

            visible = true;
        } else {
            if (visible) {
                ++blinkCounter;
            }
            visible = false;
        }
    }

    @Override
    public void draw(Batch batch) {
        if (visible) {
            super.draw(batch);
        }
    }

    public boolean stoppedBlinking() {
        return blinkCounter >= nbBlink;
    }
}
