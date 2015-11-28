package com.mygdx.userinterface.elements;

import com.mygdx.userinterface.views.GameView;

public class ResumeLabel extends Label{
    private GameView gameView;

    public ResumeLabel(GameView gameView, float viewportHeight) {
        super(Font.CALIBRI, viewportHeight, 10);
        this.gameView = gameView;
    }

    @Override
    public void onTouched() {
        gameView.resumeGame();
    }
}
