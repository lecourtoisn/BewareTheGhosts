package com.mygdx.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.world.Grid;

public interface IEntity {
    void update(float delta);
    void draw(Batch batch);
    Grid getGrid();
    /*void moveOnGrid(int x, int y);
    void move(float x, float y);*/
    void setPosition(float x, float y);
    Position getPosition();
}
