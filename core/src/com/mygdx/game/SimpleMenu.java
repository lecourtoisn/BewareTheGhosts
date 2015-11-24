package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.screen.ScreenListener;
import com.mygdx.screen.UnStretchedScreen;
import com.mygdx.util.ScaledBitmapFont;

public class SimpleMenu extends ScreenListener{
        private static final float HEIGHT = 100;
    private UnStretchedScreen simpleMenuScreen;
    private BTGGame game;
    private ScaledBitmapFont font;

    public SimpleMenu(BTGGame game) {
        this.game = game;
        this.font = new ScaledBitmapFont("fonts/calibri.ttf", HEIGHT, 10);

        simpleMenuScreen = new UnStretchedScreen(this, HEIGHT);
        simpleMenuScreen.setInputProcessor(inputProcessor);
    }

    public void start() {
        game.setScreen(simpleMenuScreen);
    }

    public void startGameSession() {
        game.startGameSession();
    }

    @Override
    public void render(Batch batch, Camera cam) {
        float x = simpleMenuScreen.getWidth()/2;
        float y = simpleMenuScreen.getHeight()/2;
        font.drawCenter(batch, "High score : " + String.valueOf(Score.getHighScore()), x, y);
    }

    private InputProcessor inputProcessor = new InputAdapter() {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            startGameSession();
            return true;
        }

        @Override
        public boolean keyTyped(char character) {
            if (character == ' ') {
                startGameSession();
            }
            return true;
        }
    };
}
