package com.mygdx.entity;

import com.mygdx.util.Direction;

public interface MovingBehaviour {
    void setMovingDirection(Direction direction);
    void move(float delta);
}
