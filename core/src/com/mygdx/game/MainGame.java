package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.views.*;

import static com.mygdx.event.DifficultySchema.Difficulty;

public class MainGame extends Game {
    private MainView mainView;
    private HighScoreView highScoreView;
    private GameView gameView;
    private PauseView pauseView;
    private EndOfGameView endOfGameView;

    @Override
    public void create() {
        setScreen(new LoadingScreen());
    }

    public void instantiateViews() {
        mainView = new MainView();
        highScoreView = new HighScoreView();
        gameView = new GameView();
        pauseView = new PauseView();
        endOfGameView = new EndOfGameView();
    }

    public void launchMainView() {
        setScreen(mainView);
        TokenManager.decrementToken();
    }
    
    public void launchHighScoreView() {
        setScreen(highScoreView);
    }

    public void startGameSession(Difficulty difficulty) {
        if (TokenManager.hasToken()) {
            gameView.start(difficulty);
            TokenManager.decrementToken();
        } else {
            // Handle poll
            /**/TokenManager.incrementToken(1);
        }
    }

    public void launchPauseView() {
        setScreen(pauseView);
    }

    public void resumeGame() {
        gameView.resumeGame();
    }

    public void launchEndOfGameView(Difficulty difficulty, Integer score, Boolean isHighScore) {
        endOfGameView.start(difficulty, score, isHighScore);
    }
}
