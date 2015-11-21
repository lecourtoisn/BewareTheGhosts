package com.mygdx.game;

import com.mygdx.screen.SimpleMenuScreen;

public class SimpleMenu {
    private SimpleMenuScreen simpleMenuScreen;
    private BTGGame game;
    public SimpleMenu(BTGGame game) {
        this.game = game;
        simpleMenuScreen = new SimpleMenuScreen(this);
    }

    public void start() {
        game.setScreen(simpleMenuScreen);
    }

    public void startGameSession() {
        game.startGameSession();
    }
}
