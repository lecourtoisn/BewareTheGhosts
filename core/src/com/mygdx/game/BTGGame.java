package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.views.EndOfGameView;
import com.mygdx.views.GameView;
import com.mygdx.views.HighScoreView;
import com.mygdx.views.MainView;

public class BTGGame extends Game {
    private HighScoreView highScoreView;
    private MainView mainView;
    private EndOfGameView endOfGameView;

    @Override
	public void create() {
        Score.antiCheatRoutine();
        highScoreView = new HighScoreView(this);
        mainView = new MainView(this);
        endOfGameView = new EndOfGameView(this);
        launchMainView();
	}

    public void startGameSession(Difficulty difficulty) {
        GameView gameView = new GameView(this, difficulty);
        gameView.start();
    }

    public void launchScoreView() {
        highScoreView.start();
    }

    public void launchMainView() {
        mainView.start();
    }

    public void launchEndOfGameView(Difficulty difficulty, Integer score, Boolean isHighScore) {
        endOfGameView.start(difficulty, score, isHighScore);
    }
}
