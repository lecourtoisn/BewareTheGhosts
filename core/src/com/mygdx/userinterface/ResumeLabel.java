package com.mygdx.userinterface;

import com.mygdx.game.GameSession;

public class ResumeLabel extends Label{
    private GameSession gameSession;

    public ResumeLabel(GameSession gameSession, float viewportHeight) {
        super(Font.CALIBRI, viewportHeight, 10);
        this.gameSession = gameSession;
    }

    @Override
    public void onTouched() {
        gameSession.resumeGame();
    }
}
