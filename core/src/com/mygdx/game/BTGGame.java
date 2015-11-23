package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BTGGame extends Game {
    private SimpleMenu simpleMenu;

	@Override
	public void create() {
        Score.setHighScore(0);
        simpleMenu = new SimpleMenu(this);
        launchSimpleMenu();
	}

    public void startGameSession() {
        GameSession gameSession = new GameSession(this);
        gameSession.start();
    }

    public void launchSimpleMenu() {
        simpleMenu.start();
    }
}
