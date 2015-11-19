package com.mygdx.event;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Arrow;
import com.mygdx.entity.Ghost;
import com.mygdx.entity.IEntity;
import com.mygdx.entity.Position;
import com.mygdx.util.Direction;
import com.mygdx.util.Randomizer;
import com.mygdx.util.StopWatch;
import com.mygdx.world.Grid;
import com.mygdx.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class GhostAttack extends Event {
    private int nbGhost;
    private boolean sameDirection;
    private float arrowWarningDuration;

    private StopWatch stopWatch;
    private boolean arrowPhase;

    private Map<Ghost, ArrowDirectionPair> ghosts;

    public GhostAttack(World world, int nbGhost, boolean sameDirection, float arrowWarningDuration) {
        super(world);
        this.nbGhost = nbGhost;
        this.sameDirection = sameDirection;
        this.arrowWarningDuration = arrowWarningDuration;

        this.stopWatch = new StopWatch();

        this.ghosts = new HashMap<Ghost, ArrowDirectionPair>();
    }

    @Override
    protected void initialize() {
        stopWatch.start();
        Grid grid = world.getGrid();
        Direction directions[] = sameDirection ? new Direction[] {Randomizer.getDirection()} : Direction.getDirections();
        Map<Vector2, Direction> posAndDir = grid.getExternalCells(directions);
        posAndDir = Randomizer.getXInMap(nbGhost, posAndDir);

        for (Vector2 position : posAndDir.keySet()) {
            Direction direction = posAndDir.get(position).getOpposite();
            Position startingPosition = new Position(grid.getCellCenterPosition(Math.round(position.x), Math.round(position.y)));

            Ghost ghost = new Ghost(grid);
            Arrow arrow = new Arrow(grid, direction);
            ghost.setMovingDirection(direction);
            ghost.setPosition(startingPosition);
            arrow.setPosition(startingPosition);
            ghosts.put(ghost, new ArrowDirectionPair(arrow, direction));
        }

        startArrowPhase();
    }

    @Override
    protected void update(float delta) {
        if (arrowPhase && stopWatch.getMilliseconds() > arrowWarningDuration) {
            endArrowPhase();
            startGhostPhase();
        }
        if (attackOver()) {
            end();
        }
    }

    private boolean attackOver() {
        boolean outOfGrid = false;
        for (Ghost ghost : ghosts.keySet()) {
            if (ghost.hasBeenOnGrid() && ghost.isOutOfGrid()) {
                outOfGrid = true;
            }
        }
        return outOfGrid;
    }

    private void startGhostPhase() {
        world.getEntities().addAll(ghosts.keySet());
    }

    @Override
    protected void clean() {
        Set<IEntity> worldEntities = world.getEntities();
        worldEntities.removeAll(ghosts.keySet());
    }

    private void startArrowPhase() {
        world.getEntities().addAll(getArrows());
        arrowPhase = true;
    }

    private void endArrowPhase() {
        arrowPhase = false;
        Set<IEntity> worldEntities = world.getEntities();
        worldEntities.removeAll(getArrows());
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
