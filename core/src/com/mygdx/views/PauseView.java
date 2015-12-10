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
import com.mygdx.game.BTGGame;
import com.mygdx.util.International;

import static com.mygdx.util.International.Label.PAUSE;
import static com.mygdx.util.International.Label.RESUME;

public class PauseView extends ScreenAdapter {
    private Stage stage;

    public PauseView() {
        stage = new Stage(new ScreenViewport());

        Table root = new Table();
        root.setFillParent(true);
        Skin skin = BTGGame.assets.get("textures/textures.json");
        Label pause = new Label(International.get(PAUSE), skin, "secMenuLabel");
        Label resume = new Label(International.get(RESUME), skin, "secMenuLabel");

        root.add(pause);
        root.row();
        root.add(resume);

        resume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BTGGame.game.resumeGame();
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
        stage.dispose();
    }
}
