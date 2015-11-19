package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.Direction;
import com.mygdx.world.Grid;

public class Arrow extends Entity {
    private static final Texture TEXTURE = new Texture("arrow.png");
    private static final Vector2 SIZE = new Vector2(10, 10);
    //private static final Vector2 CENTER = new Vector2(SIZE).scl(2f);
    private static final Vector2 CENTER = new Vector2(5, 5);

    public Arrow(Grid grid, Direction orientation) {
        super(grid, TEXTURE, SIZE, CENTER);
        sprite.setColor(Color.LIME);
        switch (orientation) {
            case LEFT:
                sprite.flip(true, false);
                break;
            case UP:
                sprite.rotate90(false);
                break;
            case DOWN:
                sprite.rotate90(true);
                break;
        }
    }
}
