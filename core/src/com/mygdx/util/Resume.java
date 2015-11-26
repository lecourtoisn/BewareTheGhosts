package com.mygdx.util;

import com.mygdx.game.GameSession;
import com.mygdx.userinterface.Label;

public class Resume extends Label{
    private GameSession gameSession;

    public Resume(ScaledBitmapFont font, GameSession gameSession) {
        super(font);
        this.gameSession = gameSession;
    }

    @Override
    public void onTouched() {
        gameSession.resumeGame();
    }
}
