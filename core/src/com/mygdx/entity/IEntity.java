package com.mygdx.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.util.Position;

public interface IEntity {
    void update(float delta);
    void draw(Batch batch);
    boolean isVisibleOnGrid();
    void collidesWithGarry(Garry garry);

    /** Getters & setters **/
    void setPosition(float x, float y);
    void setPosition(Position position);
    Position getPosition();
    Rectangle getHitbox();
}