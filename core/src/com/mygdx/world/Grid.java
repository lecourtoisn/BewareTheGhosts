package com.mygdx.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Grid {
    public final float SIZE = 90;
    public final float RATIO = SIZE / 586f;
    public final float CELLUNIT = 94f * RATIO;
    public final Vector2 FIRSTCELL = new Vector2(40, 5).add(14f*RATIO, 13f*RATIO);

    Sprite sprite;

    public Grid(float worldWidth, float worldHeight) {
        sprite = new Sprite(new Texture("grid.png"));
        sprite.setSize(SIZE, SIZE);
        sprite.setCenter(worldWidth / 2, worldHeight / 2);
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    public Vector2 getCellUnit() {
        return new Vector2(CELLUNIT, CELLUNIT);
    }
}
