package com.mygdx.views;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.game.BTGGame;
import com.mygdx.game.Score;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;

public class SimpleMenu extends ScreenListener{
    private static final float HEIGHT = 100;

    private BTGGame game;

    private Label hsNormalLabel;
    private Label hsHardLabel;

    public SimpleMenu(BTGGame game) {
        super(HEIGHT);
        this.game = game;

        screen.setInputProcessor(inputProcessor);

        hsNormalLabel = new Label(Font.CALIBRI, HEIGHT, 10);
        hsNormalLabel.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 + 7);
        hsNormalLabel.setColor(Color.CHARTREUSE);

        hsHardLabel = new Label(Font.CALIBRI, HEIGHT, 10);
        hsHardLabel.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 - 7);
        hsHardLabel.setColor(Color.BLUE);

        manager.addElement(hsNormalLabel);
        manager.addElement(hsHardLabel);
    }

    public void start() {
        game.setScreen(screen);
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
            game.launchMainView();
            return true;
        }

        @Override
        public boolean keyTyped(char character) {
            if (character == ' ') {
                game.launchMainView();
            }
            return true;
        }
    };
}
