package com.mygdx.userinterface.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.userinterface.views.GameView;

public class PauseButton extends Widget {
    private static final Texture TEXTURE = new Texture("UI/pause.png");
    private static final Vector2 GRAPHICSIZE = new Vector2(9, 9);
    private GameView game;
    public PauseButton(GameView gameView) {
        super(TEXTURE, GRAPHICSIZE);
        this.game = gameView;
        setTexture(TEXTURE);
    }

    @Override
    public void onTouched() {
        game.launchPauseMenu();
    }
}
