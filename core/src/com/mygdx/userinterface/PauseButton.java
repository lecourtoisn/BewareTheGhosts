package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Widget;
import com.mygdx.game.GameSession;

public class PauseButton extends Widget {
    private static final Texture TEXTURE = new Texture("UI/pause.png");
    private static final Vector2 GRAPHICSIZE = new Vector2(9, 9);
    private GameSession game;
    public PauseButton(GameSession gameSession) {
        super(TEXTURE, GRAPHICSIZE);
        this.game = gameSession;
        setTexture(TEXTURE);
    }

    @Override
    public void onTouched() {
        game.launchPauseMenu();
    }
}
