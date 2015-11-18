package com.mygdx.event;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Ghost;
import com.mygdx.util.Direction;
import com.mygdx.util.Randomizer;
import com.mygdx.world.Grid;
import com.mygdx.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GhostAttack extends Event {
    private int nbGhost;
    private boolean sameDirection;
    private float arrowWarningDuration;

    private Map<Direction, Ghost> ghosts;

    public GhostAttack(World world, int nbGhost, boolean sameDirection, float arrowWarningDuration) {
        super(world);
        this.nbGhost = nbGhost;
        this.sameDirection = sameDirection;
        this.arrowWarningDuration = arrowWarningDuration;

        this.ghosts = new HashMap<Direction, Ghost>();
    }

    @Override
    protected void initialize() {
        // I create the ghosts and the ghosts but don't place them yey.
        Grid grid = world.getGrid();
        List<Vector2> initialPositions = Randomizer.getXInList(nbGhost, grid.getExternalCells());
        for (int i = 0; i < nbGhost; i++) {
            Ghost newGhost = new Ghost(grid);
            Vector2 pos = initialPositions.get(i);
            newGhost.setPosition(grid.getCellCenterPosition(Math.round(pos.x), Math.round(pos.y)));
            world.addEntity(newGhost);
            newGhost.setMovingDirection(Direction.NONE);
        }
    }

    @Override
    protected void update(float delta) {
        // Whether the arrow have already been shown or not we run them or we run the ghosts
        // Not sure it's the best solution to put that here, but we can check whether or not a ghost has collided with Garry here
        // ... but i think i shouldn't do that here, because many event can kill garry by a ghost colliding with him.
        // When our ghosts are out the grid we give a point to the player and we call end which will remove the entities to the world
    }
}
