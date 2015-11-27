package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.event.DifficultySchema.Difficulty;

public class BTGGame extends Game {
    private SimpleMenu simpleMenu;
    private MainMenu mainMenu;

	@Override
	public void create() {
        Score.antiCheatRoutine();
        simpleMenu = new SimpleMenu(this);
        mainMenu = new MainMenu(this);
        launchMainMenu();
	}

    public void startGameSession(Difficulty difficulty) {
        GameSession gameSession = new GameSession(this, difficulty);
        gameSession.start();
    }

    public void launchSimpleMenu() {
        simpleMenu.start();
    }

    public void launchMainMenu() {
        mainMenu.start();
    }
}
