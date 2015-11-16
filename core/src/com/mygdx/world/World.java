package com.mygdx.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.entity.Garry;
import com.mygdx.entity.IEntity;
import com.mygdx.util.StopWatch;

import java.util.List;

public class World {
    public final float WIDTH = 170;
    public final float HEIGHT = 100;
    public final float GRIDSIZE = 90;

    private Background background;
    private Grid grid;
    private Garry garry;
    private List<IEntity> entities;

    private StopWatch stopWatch = new StopWatch();

    public World() {
        this.background = new Background(WIDTH, HEIGHT);
        this.grid = new Grid(GRIDSIZE, WIDTH, HEIGHT);
        this.garry = new Garry(grid);
        stopWatch.start();
    }



    public void update(float delta) {
        garry.update(delta);
        /*if (stopWatch.getMilliseconds() > 1000) {
            if (bli-- > 0) {
                garry.setMovingDirection(Direction.RIGHT);
            }
            else {
                garry.getCellPosition().setPosition(grid.getCellPosition(0, 0));
                bli = 5;
            }

            stopWatch.restart();
        }*/
    }

    public void render(Batch batch) {
        background.draw(batch);
        grid.draw(batch);
        garry.draw(batch);
    }
}
