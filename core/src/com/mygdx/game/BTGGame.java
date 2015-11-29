package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.views.*;

public class BTGGame extends Game {
    private HighScoreView highScoreView;
    private MainView mainView;
    private EndOfGameView endOfGameView;
    private GameView currentGameView;
    private PauseView pauseView;

    @Override
	public void create() {
        TokenManager.initialize();
        ScoreManager.antiCheatRoutine();
        highScoreView = new HighScoreView(this);
        mainView = new MainView(this);
        endOfGameView = new EndOfGameView(this);
        pauseView = new PauseView(this);
        launchMainView();
	}

    public void startGameSession(Difficulty difficulty) {
        if (TokenManager.hasToken()) {
            currentGameView = new GameView(this, difficulty);
            currentGameView.start();
            TokenManager.decrementToken();
        } else {
            System.out.println("Vous n'avez pas assez de token");
        }
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

    @Override
    public void dispose() {
        TokenManager.save();
    }

    public void resumeGame() {
        currentGameView.resumeGame();
    }

    public void launchPauseMenu() {
        pauseView.start();
    }
}
