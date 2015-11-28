package com.mygdx.userinterface.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class MainBackground extends Widget {
    private static final Texture TEXTURE = new Texture("UI/mainBackground.png");

    public MainBackground(float width, float height) {
        super(TEXTURE, new Vector2(width, height));
        sprite.setColor(Color.DARK_GRAY);
        setOrigin(0, 0);
    }
}
