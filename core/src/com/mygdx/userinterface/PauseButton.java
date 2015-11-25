package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameSession;

public class PauseButton extends UIElement{
    private static final Texture TEXTURE = new Texture("UI/pause.png");
    private GameSession game;
    public PauseButton(UIManager uiManager, GameSession gameSession) {
        super(uiManager);
        this.game = gameSession;
        setTexture(TEXTURE);
    }

    @Override
    public void onTouched() {
        game.launchPauseMenu();
    }
}
