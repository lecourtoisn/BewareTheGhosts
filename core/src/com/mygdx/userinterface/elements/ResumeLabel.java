package com.mygdx.userinterface.elements;

import com.mygdx.game.BTGGame;
import com.mygdx.views.GameView;

public class ResumeLabel extends Label{
    private BTGGame game;

    public ResumeLabel(BTGGame game, float viewportHeight) {
        super(Font.CALIBRI, viewportHeight, 10);
        this.game = game;
    }

    @Override
    public void onTouched() {
        game.resumeGame();
    }
}
