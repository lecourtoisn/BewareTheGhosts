package com.mygdx.entity;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.movingbehaviour.IMovingBehaviour;
import com.mygdx.movingbehaviour.TeleportBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.util.Position;
import com.mygdx.world.World;

import java.util.Set;

public class Garry extends WorldEntity implements IMovingBehaviour {

    private IMovingBehaviour movingStrategy;
    private boolean dead;
    private int attackAvoided;

    public Garry(World world) {
        super(world, EntityInfo.GARRY);
        this.attackAvoided = 0;
        this.dead = false;
        movingStrategy = new TeleportBehaviour(this);
        setPosition(getGrid().getCellCenterPosition(2, 2));
    }

    @Override
    public void update(float delta) {
        if (!dead) {
            Set<WorldEntity> colliding = world.getCollisions(this);
            if (!colliding.isEmpty()) {
                for (WorldEntity entity : colliding) {
                    entity.whenCollidesWithGarry(this);
                }
            }
            move(delta);
        }
    }

    /** Private & protected **/
    @Override
    public boolean couldBeAt(Position pos) {
        Rectangle gridHitbox = getGrid().getBoundaries();
        Rectangle entityHitbox = getHitbox();
        Rectangle nextEntityHitbox = new Rectangle(entityHitbox).setCenter(pos.getPosition());

        boolean pathFree = !world.enemyInPath(getPathRectangle(entityHitbox, nextEntityHitbox));
        return gridHitbox.contains(nextEntityHitbox) && pathFree;
    }

    private Rectangle getPathRectangle(Rectangle r1, Rectangle r2) {
        float pathMinX = Math.min(r1.x, r2.x);
        float pathMinY = Math.min(r1.y, r2.y);
        float pathMaxX = Math.max(r1.x, r2.x);
        float pathMaxY = Math.max(r1.y, r2.y);
        return new Rectangle(pathMinX, pathMinY, pathMaxX-pathMinX+r1.width, pathMaxY-pathMinY+r1.height);
    }

    /** Strategy pattern **/
    @Override
    public void setMovingDirection(Direction direction) {
        movingStrategy.setMovingDirection(direction);
    }

    @Override
    public void move(float delta) {
        movingStrategy.move(delta);
    }

    public void getsHurt() {
        if (!dead) {
            dead = true;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void incrementAttackAvoided() {
        attackAvoided += 1;
    }

    public int getAttackAvoided() {
        return attackAvoided;
    }
}
