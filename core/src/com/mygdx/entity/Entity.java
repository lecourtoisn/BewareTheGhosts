package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.world.Grid;

public abstract class Entity implements IEntity {
    private Grid grid;
    private Position position;
    private Vector2 hitboxSize;
    private Vector2 center;

    protected Sprite sprite;

    public Entity(Grid grid, Texture spriteTexture, Vector2 spriteSize, Vector2 center) {
        this.grid = grid;
        this.center = center;
        this.position = new Position(0, 0);
        this.sprite = new Sprite(spriteTexture);
        this.sprite.setSize(spriteSize.x, spriteSize.y);
        this.hitboxSize = new Vector2();
    }

    public void setHitboxSize(float x, float y) {
        this.hitboxSize = new Vector2(x, y);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(Batch batch) {
        Vector2 spritePos = new Vector2(getPosition().getPosition());
        spritePos.sub(center);
        sprite.setPosition(spritePos.x, spritePos.y);
        sprite.draw(batch);
    }

    /*@Override
    public void draw(Batch batch, OrthographicCamera cam) {
        Vector2 spritePos = new Vector2(getPosition().getPosition());
        spritePos.sub(center);
        sprite.setPosition(spritePos.x, spritePos.y);
        //sprite.draw(batch, cam);

    }*/

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(float x, float y) {
        position.setPosition(x, y);
    }

    @Override
    public void setPosition(Position position) {
        setPosition(position.getX(), position.getY());
    }

    @Override
    public Rectangle getHitbox() {
        Rectangle hitbox = new Rectangle();
        hitbox.setCenter(position.getPosition());
        hitbox.setSize(hitboxSize.x, hitboxSize.y);
        return hitbox;
    }

    public boolean couldBeAt(Position nextPos) {
        return true;
    }

    @Override
    public boolean isVisibleOnGrid() {
        Rectangle gridHitbox = grid.getBoundaries();
        Rectangle spriteHitBox = sprite.getBoundingRectangle();
        return gridHitbox.overlaps(spriteHitBox);
    }
}
