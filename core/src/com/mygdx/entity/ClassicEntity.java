package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.Position;

public class ClassicEntity {
    protected Texture texture;
    protected Position position;
    protected Vector2 origin;
    protected Vector2 graphicSize;

    public ClassicEntity(Texture texture, Vector2 graphicSize) {
        this.texture = texture;
        this.graphicSize = graphicSize;

        this.position = new Position(0, 0);
        this.origin = new Vector2(graphicSize).scl(0.5f);
    }

    public void setPosition(float x, float y) {
        position = new Position(x, y);
    }

    public void setPosition(Position position) {
        this.position = new Position(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setOrigin(float x, float y) {
        origin = new Vector2(x, y);
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public void setGraphicSize(float x, float y) {
        graphicSize.x = x;
        graphicSize.y = y;
    }

    public Vector2 getGraphicSize() {
        return graphicSize;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Rectangle getGraphicBounds() {
        Vector2 graphPos = getGraphicBottomLeftPosition();
        return new Rectangle(graphPos.x, graphPos.y, graphicSize.x, graphicSize.y);
    }

    public void draw(Batch batch) {
        Rectangle bounds = getGraphicBounds();
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

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
