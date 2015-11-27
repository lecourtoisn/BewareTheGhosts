package com.mygdx.event;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Arrow;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.EntityInfo;
import com.mygdx.util.*;
import com.mygdx.world.Grid;
import com.mygdx.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class GhostAttack extends Event {
    private boolean arrowPhase;
    private boolean ghostsHasShown;

    private Map<Enemy, ArrowDirectionPair> ghosts;
    private boolean someGhostsAreVisible;

    public GhostAttack(World world, int nbGhost, boolean sameDirection, int nbArrowBlink, long arrowCycleDuration) {
        super(world);
        nbGhost = nbGhost == 0 ? 1 : nbGhost;
        this.ghosts = new HashMap<Enemy, ArrowDirectionPair>();

        Grid grid = world.getGrid();

        // Generate ghosts positions
        Direction directions[] = sameDirection ? new Direction[] {Randomizer.getDirection()} : Direction.getDirections();
        Map<Vector2, Direction> posAndDir = grid.getExternalCells(directions);
        posAndDir = Randomizer.getXInMap(nbGhost, posAndDir);


        // Create ghosts and arrows, and position them
        for (Vector2 position : posAndDir.keySet()) {
            Direction direction = posAndDir.get(position).getOpposite();
            Position startingPosition = new Position(grid.getCellCenterPosition(Math.round(position.x), Math.round(position.y)));

            Enemy ghost = new Enemy(world, EntityInfo.GHOST);
            Arrow arrow = new Arrow(world, direction, nbArrowBlink, arrowCycleDuration);

            ghost.setMovingDirection(direction);

            ghost.setPosition(startingPosition);
            arrow.setPosition(startingPosition);

            ghosts.put(ghost, new ArrowDirectionPair(arrow, direction));
        }

    }

    @Override
    protected void run() {
        stopWatch.start();
        startArrowPhase();
    }

    @Override
    protected void update(float delta) {
        this.someGhostsAreVisible = someGhostsAreVisible();
        if (!ghostsHasShown && someGhostsAreVisible) {
            this.ghostsHasShown = true;
        }
        if (arrowPhase && arrowsStopBlinking()) {
            endArrowPhase();
            startGhostPhase();
        }
    }

    /**
     * Ends if the attack is over
     */
    @Override
    protected boolean mustEnd() {
        boolean attackAvoided = !someGhostsAreVisible;
        if (ghostsHasShown && attackAvoided) {
            world.getGarry().incrementAttackAvoided();
            return true;
        }
        return false;
    }

    @Override
    protected void terminate() {
        world.getEntities().removeAll(ghosts.keySet());
    }

    private boolean someGhostsAreVisible() {
        boolean ghostsAreVisible = false;
        for (Enemy ghost : ghosts.keySet()) {
            if (ghost.isVisibleOnGrid()) {
                ghostsAreVisible = true;
            }
        }
        return ghostsAreVisible;
    }

    private boolean arrowsStopBlinking() {
        for (Arrow arrow : getArrows()) {
            if (arrow.stoppedBlinking()) {
                return true;
            }
        }
        return false;
    }

    private void startGhostPhase() {
        world.getEntities().addAll(ghosts.keySet());
    }

    private void startArrowPhase() {
        world.getEntities().addAll(getArrows());
        arrowPhase = true;
    }

    private void endArrowPhase() {
        arrowPhase = false;
        world.getEntities().removeAll(getArrows());
    }

    public Set<Arrow> getArrows() {
        Set<Arrow> arrows = new HashSet<Arrow>();
        for (ArrowDirectionPair arrowDirectionPair : ghosts.values()) {
            arrows.add(arrowDirectionPair.getArrow());
        }
        return arrows;
    }

    private class ArrowDirectionPair {
        private Arrow arrow;
        private Direction direction;

        public ArrowDirectionPair(Arrow arrow, Direction direction) {
            this.arrow = arrow;
            this.direction = direction;
        }

        public Arrow getArrow() {
            return arrow;
        }

        public Direction getDirection() {
            return direction;
        }
    }
}
