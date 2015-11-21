package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.commandhandlers.InputHandler;
import com.mygdx.world.World;

public class GameScreen extends ScreenAdapter {
    private OrthographicCamera cam;
    private SpriteBatch batch;

    private World world;

    public GameScreen(World world) {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, world.WIDTH, world.HEIGHT);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        this.world = world;
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
        world.render(batch, cam);
        batch.end();
    }
}
