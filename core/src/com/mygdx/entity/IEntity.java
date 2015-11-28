package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.Position;

public interface IEntity {
    void draw(Batch batch);
    void update(float delta);

    /** Getters & setters **/
    void setPosition(float x, float y);
    void setPosition(Position position);
    Position getPosition();
    void setOrigin(float x, float y);
    void setOriginX(float x);
    void setOriginY(float y);
    Vector2 getOrigin();
    void setGraphicSize(float x, float y);
    Vector2 getGraphicSize();
    float getGSizeX();
    float getGSizeY();
    void setTexture(Texture texture);
    Rectangle getGraphicBounds();
}