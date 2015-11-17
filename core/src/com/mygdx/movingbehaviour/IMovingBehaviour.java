package com.mygdx.movingbehaviour;

import com.mygdx.util.Direction;

public interface IMovingBehaviour {
    void setMovingDirection(Direction direction);
    void move(float delta);
}
