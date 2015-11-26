package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.event.DifficultySchema.*;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.Label;
import com.mygdx.userinterface.ScaledBitmapFont;

public class SimpleMenu extends ScreenListener{
    private static final float HEIGHT = 100;

    private BTGGame game;
    private ScaledBitmapFont normalFont;
    private ScaledBitmapFont hardFont;

    private Label hsNormalLabel;
    private Label hsHardLabel;

    public SimpleMenu(BTGGame game) {
        super(HEIGHT);
        this.game = game;
        this.normalFont = new ScaledBitmapFont("fonts/calibri", HEIGHT, 10);
        this.hardFont = new ScaledBitmapFont("fonts/calibri", HEIGHT, 10);
        normalFont.setColor(Color.CHARTREUSE);
        hardFont.setColor(Color.BLUE);

        screen.setInputProcessor(inputProcessor);

        hsNormalLabel = new Label(normalFont);
        hsHardLabel = new Label(hardFont);
        hsNormalLabel.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 + 7);
        hsHardLabel.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 - 7);

        manager.addElement(hsNormalLabel);
        manager.addElement(hsHardLabel);
    }

    public void start() {
        game.setScreen(screen);
    }

    public void startGameSession() {
        game.startGameSession();
    }

    @Override
    public void update(float delta) {
        hsNormalLabel.setText(String.valueOf("Normal HighScore: " + Score.getHighScore(Difficulty.NORMAL)));
        hsHardLabel.setText(String.valueOf("Hard HighScore : " + Score.getHighScore(Difficulty.HARD)));
    }

    @Override
    public void render(Batch batch, Camera cam) {
        manager.draw(batch);
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
