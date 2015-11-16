package com.mygdx.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Background {
    Sprite sprite;

    public Background(float width, float height) {
        sprite = new Sprite(new Texture("background.png"));
        sprite.setSize(width, height);
        sprite.setPosition(0, 0);
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }
}
