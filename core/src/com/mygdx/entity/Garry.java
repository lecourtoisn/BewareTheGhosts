package com.mygdx.entity;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.movingbehaviour.IMovingBehaviour;
import com.mygdx.movingbehaviour.TeleportBehaviour;
import com.mygdx.util.Direction;
import com.mygdx.util.Position;
import com.mygdx.world.World;

import java.util.Set;

public class Garry extends Entity implements IMovingBehaviour {

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
            Set<Entity> colliding = world.getCollisions(this);
            if (!colliding.isEmpty()) {
                for (Entity entity : colliding) {
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
        entityHitbox.setCenter(pos.getPosition());

        return gridHitbox.contains(entityHitbox);
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
