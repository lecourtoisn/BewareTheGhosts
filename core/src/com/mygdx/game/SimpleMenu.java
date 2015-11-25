package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.Label;
import com.mygdx.util.ScaledBitmapFont;

public class SimpleMenu extends ScreenListener{
    private static final float HEIGHT = 100;

    private BTGGame game;
    private ScaledBitmapFont font;

    private Label highScoreLabel;

    public SimpleMenu(BTGGame game) {
        super(HEIGHT);
        this.game = game;
        this.font = new ScaledBitmapFont("fonts/calibri", HEIGHT, 10);
        font.setColor(Color.CHARTREUSE);

        screen.setInputProcessor(inputProcessor);

        highScoreLabel = new Label(manager, font);
        highScoreLabel.setPosition(screen.getWidth() / 2, screen.getHeight() / 2);
    }

    public void start() {
        game.setScreen(screen);
    }

    public void startGameSession() {
        game.startGameSession();
    }

    @Override
    public void update(float delta) {
        highScoreLabel.setText(String.valueOf("HighScore : " + Score.getHighScore()));
    }

    @Override
    public void render(Batch batch, Camera cam) {
        highScoreLabel.draw(batch);
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
