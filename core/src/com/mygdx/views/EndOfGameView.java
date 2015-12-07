package com.mygdx.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.util.International;

import static com.mygdx.event.DifficultySchema.Difficulty;
import static com.mygdx.game.BTGGame.assets;
import static com.mygdx.game.BTGGame.game;
import static com.mygdx.util.International.Label.*;

public class EndOfGameView extends ScreenAdapter {
    Stage stage;
    private Difficulty difficulty;
    private Label scoreLabel;

    public EndOfGameView() {
        stage = new Stage(new ScreenViewport());
        Table root = new Table();
        root.setFillParent(true);
        Skin skin = assets.get("textures/textures.json");
        scoreLabel = new Label("", skin, "finalScoreLabel");
        Label playAgain = new Label(International.get(PLAYAGAIN), skin, "resumeLabel");
        Label menu = new Label(International.get(MENU), skin, "resumeLabel");

        root.add(scoreLabel).colspan(3);
        root.row();
        root.add(playAgain).right();
        root.add().space(0, 20, 0, 20);
        root.add(menu).left();

        playAgain.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.startGameSession(difficulty);
            }
        });
        menu.addListener(new ClickListener() {
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
        stage.getViewport().update(width, height);
    }

    @Override
    public void render(float delta) {
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void start(Difficulty difficulty, Integer score, Boolean isHighScore) {
        game.setScreen(this);
        this.difficulty = difficulty;
        String text = isHighScore ? International.get(NEWHIGHSCORE) : International.get(SCORE);
        text += " : " + score;
        text += isHighScore ? "!" : "";
        scoreLabel.setText(text);
    }
}
