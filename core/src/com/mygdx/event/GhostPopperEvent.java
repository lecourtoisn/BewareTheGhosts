package com.mygdx.event;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Ghost;
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
        int nbGhost = 6;
        Direction movingDirection = Randomizer.getDirection();
        Grid grid = world.getGrid();
        Map<Vector2, Direction> posAndDir = grid.getExternalCells();
        posAndDir = Randomizer.getXInMap(nbGhost, posAndDir);

        for (Vector2 pos : posAndDir.keySet()) {
            Ghost newGhost = new Ghost(grid);
            newGhost.setPosition(grid.getCellCenterPosition(Math.round(pos.x), Math.round(pos.y)));
            world.addEntity(newGhost);
            newGhost.setMovingDirection(posAndDir.get(pos).getOpposite());
        }
    }


}
