package com.mygdx.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.userinterface.UIManager;

public class ScreenListener implements IScreenListener {

    protected UnStretchedScreen screen;
    protected UIManager manager;

    public ScreenListener(float height) {
        screen = new UnStretchedScreen(this, height);
        manager = new UIManager();
    }

    @Override
    public void show() {
        // Does nothing by default
    }

    @Override
    public void pause() {
        // Does nothing by default
    }

    @Override
    public void resize(int width, int height) {
        // Does nothing by default
    }

    @Override
    public void hide() {
        // Does nothing by default
    }

    @Override
    public void dispose() {
        // Does nothing by default
    }

    @Override
    public void render(Batch batch, Camera cam) {
        // Does nothing by default
    }

    @Override
    public void update(float delta) {
        // Does nothing by default
    }

    @Override
    public void resume() {
        // Does nothing by default
    }
}
