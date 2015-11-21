package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameSession;

public class GameScreen extends ScreenAdapter {
    private GameSession gameSession;

    private OrthographicCamera cam;
    private SpriteBatch batch;

    private InputProcessor inputProcessor;

    public GameScreen(GameSession gameSession, InputProcessor inputProcessor, float width, float height) {
        this.gameSession = gameSession;
        this.inputProcessor = inputProcessor;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, width, height);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    /**
     * Game loop
     */
    @Override
    public void render(float delta) {
        gameSession.update(delta);
        batch.begin();
        gameSession.render(batch, cam);
        batch.end();
    }
}
