package refact;

import com.badlogic.gdx.Game;

import static com.mygdx.event.DifficultySchema.Difficulty;

public class MainGame extends Game {
    private MainView mainView;
    private HighScoreView highScoreView;
    private GameView gameView;
    @Override
    public void create() {
        setScreen(new LoadingScreen());
    }

    public void instantiateViews() {
        mainView = new MainView();
        highScoreView = new HighScoreView();
        gameView = new GameView();
    }

    public void launchMainView() {
        setScreen(mainView);
    }
    
    public void launchHighScoreView() {
        setScreen(highScoreView);
    }

    public void startGameSession(Difficulty difficulty) {
        gameView.start(difficulty);
    }
}
