package com.mygdx.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.world.Grid;

public interface IEntity {
    void update(float delta);
    void draw(Batch batch);
    Grid getGrid();
    void setPosition(float x, float y);
    Position getPosition();
    Rectangle getHitbox();
}
