package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.views.GameView;
import com.mygdx.views.MainView;
import com.mygdx.views.SimpleMenu;

public class BTGGame extends Game {
    private SimpleMenu simpleMenu;
    private MainView mainView;

	@Override
	public void create() {
        Score.antiCheatRoutine();
        simpleMenu = new SimpleMenu(this);
        mainView = new MainView(this);
        launchMainMenu();
	}

    public void startGameSession(Difficulty difficulty) {
        GameView gameView = new GameView(this, difficulty);
        gameView.start();
    }

    public void launchSimpleMenu() {
        simpleMenu.start();
    }

    public void launchMainMenu() {
        mainView.start();
    }
}
