package com.mygdx.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.TokenManager;
import com.mygdx.util.International;

import static com.mygdx.event.DifficultySchema.Difficulty;
import static com.mygdx.game.BTGGame.game;
import static com.mygdx.game.BTGGame.skin;

public class MainView extends ScreenAdapter {
    private Stage stage;
    private Label tokenQuantity;
    private Label tokenCountdown;

    public MainView() {
//        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
//        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage = new Stage(new ScreenViewport());

        int pad = Gdx.graphics.getHeight() / 10;
        Label title = new Label(International.get(International.Label.TITLE), skin, "title");
        Label normal = new Label(International.get(International.Label.NORMALLBL), skin, "mainLabel");
        Label hard = new Label(International.get(International.Label.HARDLBL), skin, "mainLabel");
        Label highScore = new Label(International.get(International.Label.HIGHSCORE), skin, "mainLabel");
        tokenQuantity = new Label("", skin, "tokenQuantity");
        tokenCountdown = new Label("", skin, "tokenCountdown");

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
