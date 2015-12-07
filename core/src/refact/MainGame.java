package refact;

import com.badlogic.gdx.Game;

public class MainGame extends Game {
    private MainView mainView;
    private HighScoreView highScoreView;

    @Override
    public void create() {
        setScreen(new LoadingScreen());
    }

    public void instanciateViews() {
        mainView = new MainView();
        highScoreView = new HighScoreView();
    }

    public void launchMainView() {
        setScreen(mainView);
    }
    
    public void launchHighScoreView() {
        setScreen(highScoreView);
    }
}
