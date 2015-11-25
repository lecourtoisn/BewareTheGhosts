package com.mygdx.util;

import com.mygdx.game.GameSession;
import com.mygdx.userinterface.Label;
import com.mygdx.userinterface.UIManager;

public class Resume extends Label{
    private GameSession gameSession;

    public Resume(UIManager uiManager, ScaledBitmapFont font, GameSession gameSession) {
        super(uiManager, font);
        this.gameSession = gameSession;
    }

    @Override
    public void onTouched() {
        gameSession.resumeGame();
    }
}
