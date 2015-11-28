package com.mygdx.userinterface.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Background extends Widget {


    private static final Texture TEXTURE = new Texture("background.png");

    public Background(float width, float height) {
        super(TEXTURE, new Vector2(width, height));
        setOrigin(0, 0);
    }
}
