package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.Texture;

public class PauseButton extends UIElement{
    private static final Texture TEXTURE = new Texture("UI/pause.png");
    public PauseButton(UIManager uiManager) {
        super(uiManager);
        setTexture(TEXTURE);
    }

    @Override
    public void onTouched() {
        System.out.println("Pause !");
    }
}
