package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.world.Grid;

public class Ghost extends Entity {
    private static final Texture TEXTURE = new Texture("ghost.png");
    private static final float WIDTH = 6.2f;
    private static final float HEIGHT = 9;
    private static final Vector2 CENTER = new Vector2(WIDTH/2, HEIGHT/2);

    public Ghost(Grid grid) {
        super(grid, TEXTURE, new Vector2(WIDTH, HEIGHT), CENTER);
        setHitboxSize(WIDTH, HEIGHT);

        setPosition(grid.getCellCenterPosition(1, 1));

        sprite.setColor(Color.LIME);
    }
}
