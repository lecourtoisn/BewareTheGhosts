package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.views.*;

public class BTGGame extends Game {
    private HighScoreView highScoreView;
    private MainView mainView;
    private EndOfGameView endOfGameView;
    private GameView gameView;
    private PauseView pauseView;

    @Override
	public void create() {
        TokenManager.initialize();
        ScoreManager.antiCheatRoutine();
        highScoreView = new HighScoreView(this);
        mainView = new MainView(this);
        endOfGameView = new EndOfGameView(this);
        pauseView = new PauseView(this);
        gameView = new GameView(this);
        launchMainView();
	}

    @Override
    public void pause() {
        super.pause();
        TokenManager.save();
    }

    @Override
    public void dispose() {
/*
        super.dispose();
        for (EntityInfo entityInfo : EntityInfo.values()) {
            entityInfo.getTexture().dispose();
        }
*/
    }

    public void startGameSession(Difficulty difficulty) {
        //long aaa = System.currentTimeMillis();
        if (TokenManager.hasToken()) {
            gameView.start(difficulty);
            TokenManager.decrementToken();
        } else {
            // Run the poll
        }
        //System.out.println(System.currentTimeMillis() - aaa);
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

    public void resumeGame() {
        gameView.resumeGame();
    }

    public void launchPauseMenu() {
        pauseView.start();
    }
}
