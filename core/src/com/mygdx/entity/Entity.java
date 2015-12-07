package com.mygdx.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.Position;

public class Entity implements IEntity{
    protected Position position;
    protected Vector2 origin;
    protected Vector2 graphicSize;
    protected Sprite sprite;

    public Entity(TextureRegion texture, Vector2 graphicSize) {
        this.graphicSize = graphicSize;
        this.position = new Position(0, 0);
        this.origin = new Vector2(graphicSize).scl(0.5f);
        this.sprite = new Sprite(texture);
    }

    @Override
    public void setPosition(float x, float y) {
        position = new Position(x, y);
    }

    @Override
    public void setPosition(Position position) {
        this.position = new Position(position);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public float getPosX() {
        return position.getX();
    }

    @Override
    public float getPosY() {
        return position.getY();
    }

    @Override
    public void setOrigin(float x, float y) {
        origin = new Vector2(x, y);
    }

    @Override
    public void setOriginX(float x) {
        origin.x = x;
    }

    @Override
    public void setOriginY(float y) {
        origin.y = y;
    }

    @Override
    public Vector2 getOrigin() {
        return origin;
    }

    @Override
    public void setGraphicSize(float x, float y) {
        graphicSize.x = x;
        graphicSize.y = y;
    }

    @Override
    public Vector2 getGraphicSize() {
        return graphicSize;
    }

    @Override
    public float getGSizeX() {
        return graphicSize.x;
    }

    @Override
    public float getGSizeY() {
        return graphicSize.y;
    }

    @Override
    public void setTexture(TextureRegion texture) {
        sprite.setRegion(texture);
    }

    @Override
    public Rectangle getGraphicBounds() {
        Vector2 graphPos = getGraphicBottomLeftPosition();
        return new Rectangle(graphPos.x, graphPos.y, graphicSize.x, graphicSize.y);
    }

    @Override
    public float getGPosX() {
        return getGraphicBottomLeftPosition().x;
    }
    @Override
    public float getGPosY() {
        return getGraphicBottomLeftPosition().y;
    }

    @Override
    public void draw(Batch batch) {
        Rectangle bounds = getGraphicBounds();
        sprite.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        sprite.draw(batch);
    }

    @Override
    public void update(float delta) {
        // Does nothing by default
    }

    /** Private methods **/
    private Vector2 getGraphicBottomLeftPosition() {
        Vector2 pos = new Vector2(position.getPosition());
        pos.sub(origin);
        return pos;
    }
}
