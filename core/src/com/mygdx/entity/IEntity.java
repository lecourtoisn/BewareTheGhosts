package com.mygdx.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.world.Grid;

public interface IEntity {
    void update(float delta);
    void draw(Batch batch);

    /** Getters & setters **/
    Grid getGrid();
    void setPosition(float x, float y);
    void setPosition(Position position);
    Position getPosition();
    Rectangle getHitbox();
    boolean isOutOfGrid();
    boolean hasBeenOnGrid();
}