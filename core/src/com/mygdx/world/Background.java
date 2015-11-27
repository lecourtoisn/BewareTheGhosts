package com.mygdx.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.userinterface.Widget;

public class Background extends Widget {


    private static final Texture TEXTURE = new Texture("background.png");

    public Background(float width, float height) {
        super(TEXTURE, new Vector2(width, height));
        setOrigin(0, 0);
    }
}
