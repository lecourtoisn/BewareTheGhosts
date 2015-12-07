package refact;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.TokenManager;

import static com.mygdx.event.DifficultySchema.Difficulty;

public class MainGame extends Game {
    private MainView mainView;
    private HighScoreView highScoreView;
    private GameView gameView;
    private Screen pauseView;

    @Override
    public void create() {
        setScreen(new LoadingScreen());
    }

    public void instantiateViews() {
        mainView = new MainView();
        highScoreView = new HighScoreView();
        gameView = new GameView();
        pauseView = new PauseView();
    }

    public void launchMainView() {
        setScreen(mainView);
        TokenManager.decrementToken();
    }
    
    public void launchHighScoreView() {
        setScreen(highScoreView);
    }

    public void startGameSession(Difficulty difficulty) {
        gameView.start(difficulty);
    }

    public void launchPauseView() {
        setScreen(pauseView);
    }

    public void resumeGame() {
        gameView.resumeGame();
    }
}
