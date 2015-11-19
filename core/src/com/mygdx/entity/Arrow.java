package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.Direction;
import com.mygdx.util.StopWatch;
import com.mygdx.world.Grid;

public class Arrow extends Entity {
    private static final Texture TEXTURE = new Texture("arrow.png");
    private static final Vector2 SIZE = new Vector2(10, 10);
    private static final Vector2 CENTER = new Vector2(SIZE).scl(1/2f);
    private static final float DISPLAYED = 300;
    private static final float HIDDEN = 100;

    private StopWatch stopWatch;

    public Arrow(Grid grid, Direction orientation) {
        super(grid, TEXTURE, SIZE, CENTER);
        this.stopWatch = new StopWatch();
        stopWatch.start();
        sprite.setColor(Color.LIME);
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
        float phase = stopWatch.getMilliseconds() % (DISPLAYED + HIDDEN);
        if (phase < DISPLAYED) {
            super.draw(batch);
        }
    }
}
