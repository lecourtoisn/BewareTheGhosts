package com.mygdx.event;

import com.mygdx.entity.Ghost;
import com.mygdx.util.Direction;
import com.mygdx.util.Randomizer;
import com.mygdx.util.StopWatch;
import com.mygdx.world.Grid;
import com.mygdx.world.World;

public class GhostPopperEvent extends Event {
    private StopWatch stopWatch;

    public GhostPopperEvent(World world) {
        super(world);
        stopWatch = new StopWatch();
    }

    @Override
    protected void initialize() {
        System.out.println("START : Ghosts are coming !");
        stopWatch.start();
    }

    @Override
    protected void clean() {
        System.out.println("END : No more ghosts !");
        stopWatch.stop();
    }

    @Override
    public void update(float delta) {
        if (stopWatch.getSeconds() > 1) {
            popAGhost();
            stopWatch.restart();
        }
    }

    private void popAGhost() {
        int x = Randomizer.getInt(6), y = Randomizer.getInt(6);
        Direction movingDirection = Randomizer.getDirection();
        Grid grid = world.getGrid();
        Ghost newGhost = new Ghost(grid);
        world.addEntity(newGhost);
        newGhost.setPosition(grid.getCellCenterPosition(x, y));
        newGhost.setMovingDirection(movingDirection);
    }


}
