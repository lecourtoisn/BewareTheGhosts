package com.mygdx.userinterface;

import com.mygdx.game.GameSession;

public class ResumeLabel extends Label{
    private GameSession gameSession;

    public ResumeLabel(ScaledBitmapFont font, GameSession gameSession) {
        super(font);
        this.gameSession = gameSession;
    }

    @Override
    public void onTouched() {
        gameSession.resumeGame();
    }
}
