package refact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.TokenManager;
import com.mygdx.util.International;

import static com.mygdx.event.DifficultySchema.Difficulty;
import static refact.BTGGame.assets;
import static refact.BTGGame.game;

public class MainView extends ScreenAdapter {
    private Stage stage;
    private Label tokenQuantity;
    private Label tokenCountdown;

    public MainView() {
//        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
//        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage = new Stage(new ScreenViewport());

        Skin skin = assets.get("textures/textures.json");


        int pad = 50;

        Label title = new Label(International.get(International.Label.TITLE), skin, "title");
        Label normal = new Label(International.get(International.Label.NORMALLBL), skin, "buttonStyle");
        Label hard = new Label(International.get(International.Label.HARDLBL), skin, "buttonStyle");
        Label highScore = new Label(International.get(International.Label.HIGHSCORE), skin, "buttonStyle");
        tokenQuantity = new Label("30/30", skin, "tokenQuantity");
        tokenCountdown = new Label("2:57", skin, "tokenCountdown");
        Image garry = new Image(skin.getDrawable("garry"));

        final Table root = new Table();
        VerticalGroup topRight = new VerticalGroup();
        VerticalGroup bottomLeft = new VerticalGroup().left();
        root.setSkin(skin);
        root.setFillParent(true);
        root.setBackground("background");

        root.pad(pad);

        root.add(title).top().left();
        root.add(topRight).center().right().expandX();
        root.row().expandY();
        root.add(bottomLeft).bottom().left().padRight(10);
        root.add(garry).bottom().left();

        topRight.addActor(tokenQuantity);
        topRight.addActor(tokenCountdown);

        bottomLeft.addActor(normal);
        bottomLeft.addActor(hard);
        bottomLeft.addActor(highScore);

        normal.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.startGameSession(Difficulty.NORMAL);
            }
        });
        hard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.startGameSession(Difficulty.HARD);
            }
        });
        highScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.launchHighScoreView();
            }
        });

        stage.addActor(root);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        stage.draw();
        tokenCountdown.setText(TokenManager.getRemainingSecondsStr());
        tokenQuantity.setText(TokenManager.getNbToken() + "/" + TokenManager.NB_TOKEN_MAX);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
