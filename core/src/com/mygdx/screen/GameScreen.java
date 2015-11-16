package com.mygdx.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.commandhandlers.InputHandler;
import com.mygdx.game.BTGGame;
import com.mygdx.world.World;

public class GameScreen implements Screen {
    private Game game;

    private OrthographicCamera cam;
    private SpriteBatch batch;

    private World world;

    public GameScreen(BTGGame game) {
        this.game = game;
        world = new World();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, world.WIDTH, world.HEIGHT);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    /**
     * Game loop
     */
    @Override
    public void render(float delta) {
        world.update(delta);
        batch.begin();
        world.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
