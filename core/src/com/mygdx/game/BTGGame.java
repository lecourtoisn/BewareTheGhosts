package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.event.DifficultySchema.Difficulty;

public class BTGGame extends Game {
    private SimpleMenu simpleMenu;

	@Override
	public void create() {
        Score.antiCheatRoutine();
        simpleMenu = new SimpleMenu(this);
        launchSimpleMenu();
	}

    public void startGameSession() {
        GameSession gameSession = new GameSession(this, Difficulty.NORMAL);
        gameSession.start();
    }

    public void launchSimpleMenu() {
        simpleMenu.start();
    }
}
