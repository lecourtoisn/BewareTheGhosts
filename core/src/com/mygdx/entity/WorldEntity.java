package com.mygdx.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.Position;
import com.mygdx.world.Grid;
import com.mygdx.world.World;

public class WorldEntity extends ClassicEntity {
    private Vector2 hitboxSize;

    private World world;
    protected EntityInfo entityInfo;

    public WorldEntity(World world, EntityInfo entityInfo) {
        super(entityInfo.getTexture(), entityInfo.getSize());
        this.entityInfo = entityInfo;
        this.world = world;
        hitboxSize = entityInfo.getHitbox();
    }

    @Override
    public void setGraphicSize(float x, float y) {
        graphicSize.x = x;
        graphicSize.y = y;
    }

    public Rectangle getHitbox() {
        Rectangle hitbox = new Rectangle(0, 0, hitboxSize.x, hitboxSize.y);
        hitbox.setCenter(position.getX(), position.getY());
        return hitbox;
    }

    public void setHitboxSize(Vector2 hitboxSize) {
        this.hitboxSize = hitboxSize;
    }

    public boolean couldBeAt(Position nextPos)  {
        return true;
    }

    public boolean isVisibleOnGrid() {
        Rectangle gridHitbox = getGrid().getBoundaries();
        Rectangle graphicBounds = getGraphicBounds();
        return gridHitbox.overlaps(graphicBounds);
    }

    public Grid getGrid() {
        return world.getGrid();
    }

    public void whenCollidesWithGarry(Garry garry) {
        // Does nothing by default
    }
}
