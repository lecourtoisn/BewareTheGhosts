package com.mygdx.event;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Ghost;
import com.mygdx.util.Direction;
import com.mygdx.util.Randomizer;
import com.mygdx.util.StopWatch;
import com.mygdx.world.Grid;
import com.mygdx.world.World;

import java.util.List;

public class GhostPopperEvent extends Event {
    private StopWatch stopWatch;

    public GhostPopperEvent(World world) {
        super(world);
        stopWatch = new StopWatch();
    }

    @Override
    protected void initialize() {
        stopWatch.start();
    }

    @Override
    protected void clean() {
        stopWatch.stop();
    }

    @Override
    public void update(float delta) {
        if (stopWatch.getSeconds() > 1) {
            world.clearEntities();
            popAGhost();
            stopWatch.restart();
        }
    }

    private void popAGhost() {
        int nbGhost = 3;
        Direction movingDirection = Randomizer.getDirection();
        Grid grid = world.getGrid();
        List<Vector2> positions = Randomizer.getXInList(3, grid.getExternalCells());

        for (int i = 0; i < nbGhost; i++) {
            Ghost newGhost = new Ghost(grid);
            Vector2 pos = positions.get(i);
            newGhost.setPosition(grid.getCellCenterPosition(Math.round(pos.x), Math.round(pos.y)));
            world.addEntity(newGhost);
            newGhost.setMovingDirection(Direction.NONE);
        }

    }


}
