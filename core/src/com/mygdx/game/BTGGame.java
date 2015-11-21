package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BTGGame extends Game {
    private GameSession gameSession;

	@Override
	public void create() {
		gameSession = new GameSession(this);
        gameSession.start();
	}
}
