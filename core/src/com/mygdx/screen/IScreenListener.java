package com.mygdx.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

public interface IScreenListener {

    void show();

    void pause();

    void resize(int width, int height);

    void hide();

    void dispose();

    void render(Batch batch, Camera cam);

    void update(float delta);

    void resume();
}
