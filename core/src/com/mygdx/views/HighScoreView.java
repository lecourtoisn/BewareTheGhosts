package com.mygdx.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.util.International;

import static com.mygdx.event.DifficultySchema.Difficulty.HARD;
import static com.mygdx.event.DifficultySchema.Difficulty.NORMAL;
import static com.mygdx.game.BTGGame.game;
import static com.mygdx.game.BTGGame.skin;
import static com.mygdx.game.ScoreManager.getHighScore;

public class HighScoreView extends ScreenAdapter{
    private Stage stage;
    private Label normalScore;
    private Label hardScore;
    public HighScoreView() {
        stage = new Stage(new ScreenViewport());

        Table root = new Table();
        root.setSkin(skin);
        root.setFillParent(true);
        root.setBackground("background");
        Table bottomLeft = new Table();

        int pad = Gdx.graphics.getHeight() / 10;

        Label title = new Label(International.get(International.Label.HIGHSCORE), skin, "title");
        Label normal = new Label(International.get(International.Label.NORMALLBL), skin, "mainLabel");
        Label hard = new Label(International.get(International.Label.HARDLBL), skin, "mainLabel");
        normalScore = new Label("0", skin, "highScoreValue");
        hardScore = new Label("0", skin, "highScoreValue");

        root.pad(pad);

        root.row().expandX();
        root.add(title).top().left();
        root.row().expandY();
        root.add(bottomLeft).bottom().left();

        bottomLeft.row().left();
        bottomLeft.add(normal);
        bottomLeft.add(normalScore).spaceLeft(20);
        bottomLeft.row().left();
        bottomLeft.add(hard);
        bottomLeft.add(hardScore).spaceLeft(20);

        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.launchMainView();
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
        normalScore.setText(String.valueOf(getHighScore(NORMAL)));
        hardScore.setText(String.valueOf(getHighScore(HARD)));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
