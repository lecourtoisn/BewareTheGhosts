package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IUIElement {
    void setPosition(float x, float y);
    void setCentered(boolean centered);
    Vector2 getPosition();
    void setSize(float width, float height);
    void draw(Batch batch);
    void update(float delta);
    Rectangle getHitbox();

    void onTouched();
}
