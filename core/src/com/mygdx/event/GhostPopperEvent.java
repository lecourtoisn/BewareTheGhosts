package com.mygdx.event;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Arrow;
import com.mygdx.util.Direction;
import com.mygdx.util.Randomizer;
import com.mygdx.util.StopWatch;
import com.mygdx.world.Grid;
import com.mygdx.world.World;

import java.util.Map;

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
        if (stopWatch.getSeconds() > 2) {
            //world.clearEntities();
            popAGhost();
            stopWatch.restart();
        }
    }

    private void popAGhost() {
        int nbGhost = 3;
        Direction movingDirection = Randomizer.getDirection();
        Grid grid = world.getGrid();
        Map<Vector2, Direction> posAndDir = grid.getExternalCells(new Direction[] {movingDirection});
        posAndDir = Randomizer.getXInMap(nbGhost, posAndDir);

        for (Vector2 pos : posAndDir.keySet()) {
            Direction dir = posAndDir.get(pos);
            Arrow newGhost = new Arrow(grid, dir.getOpposite());
            newGhost.setPosition(grid.getCellCenterPosition(Math.round(pos.x), Math.round(pos.y)));
            world.addEntity(newGhost);
            //newGhost.setMovingDirection(dir.getOpposite());
        }
    }


}
