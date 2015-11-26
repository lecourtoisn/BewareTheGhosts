package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UnStretchedScreen extends ScreenAdapter {
    private IScreenListener listener;

    private OrthographicCamera cam;
    private SpriteBatch batch;

    private InputProcessor inputProcessor;
    private float height;
    private float width;

    public UnStretchedScreen(IScreenListener listener, float height) {
        float ratio = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        this.height = height;
        this.width = ratio *height;

        this.listener = listener;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, width, height);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputProcessor);
        listener.show();
    }

    /**
     * Game loop
     */
    @Override
    public void render(float delta) {
        listener.update(delta);

        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        listener.render(batch, cam);
        batch.end();
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public Camera getCamera() {
        return cam;
    }
}
