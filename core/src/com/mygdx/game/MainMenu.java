package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.MainBackground;
import com.mygdx.userinterface.Widget;

public class MainMenu extends ScreenListener {
    private BTGGame game;
    private Widget background;

    public MainMenu(BTGGame game) {
        super(100);
        this.game = game;
        this.background = new MainBackground(screen.getWidth(), screen.getHeight());
    }

    public void start() {
        game.setScreen(screen);
    }

    @Override
    public void render(Batch batch, Camera cam) {
        background.draw(batch);
    }
}
