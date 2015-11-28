package com.mygdx.userinterface.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.commandhandlers.GameSessionInputHandler;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.event.EndlessSalvos;
import com.mygdx.event.IEvent;
import com.mygdx.game.BTGGame;
import com.mygdx.util.CountDown;
import com.mygdx.game.Score;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;
import com.mygdx.userinterface.elements.PauseButton;
import com.mygdx.world.World;

public class GameView extends ScreenListener {
    private static final float WORLD_HEIGHT = 100;

    private IEvent event;
    private BTGGame game;
    private World world;

    // UI part
    private PauseButton pauseButton;
    private Label scoreLabel;
    private PauseView pauseView;
    private Label countDownLabel;
    private Difficulty difficulty;
    private CountDown countDown;

    public GameView(BTGGame game, Difficulty difficulty) {
        super(WORLD_HEIGHT);
        this.game = game;
        this.difficulty = difficulty;
        world = new World(screen.getWidth(), screen.getHeight());
        event = new EndlessSalvos(world, difficulty);
        pauseView = new PauseView(game, this);
        countDown = new CountDown(3);

        screen.setInputProcessor(new GameSessionInputHandler(screen, world, manager, countDown));
        initializeUI();
    }

    private void initializeUI() {
        pauseButton = new PauseButton(this);
        pauseButton.setOrigin(pauseButton.getGraphicSize().x, pauseButton.getGraphicSize().y);
        pauseButton.setPosition(screen.getWidth(), screen.getHeight());

        scoreLabel = new Label(Font.CALIBRIBOLD, WORLD_HEIGHT, 10);
        scoreLabel.setPosition(screen.getWidth() / 2, screen.getHeight() - 5);
        scoreLabel.setColor(Color.BLACK);

        countDownLabel = new Label(Font.CALIBRIBOLD, WORLD_HEIGHT, 50);
        countDownLabel.setPosition(screen.getWidth() / 2, screen.getHeight() / 2);
        countDownLabel.setColor(Color.BLACK);

        manager.addElement(scoreLabel);
        manager.addElement(pauseButton);
    }

    public void start() {
        game.setScreen(screen);
        event.start();
    }

    @Override
    public void show() {
        manager.addElement(countDownLabel);
        countDown.reset();
    }

    @Override
    public void update(float delta) {
        countDown.update(delta);
        if (countDown.isOver()) {
            manager.removeElement(countDownLabel);
            world.update(delta);
            scoreLabel.setText(String.valueOf(getScore()));
            if (event.isOver()) {
                handleScore();
                game.launchSimpleMenu();
            }
        } else {
            if (countDown.isRunning()) {
                countDownLabel.setText(countDown.getSecondStr());
            } else {
                countDownLabel.setText("Touch !");
            }
        }
    }

    @Override
    public void render(Batch batch, Camera cam) {
        world.render(batch, cam);
        manager.draw(batch);
    }

    public int getScore() {
        return world.getGarry().getAttackAvoided();
    }

    public void launchPauseMenu() {
        pauseView.start();
    }

    public void resumeGame() {
        game.setScreen(screen);
    }

    private void handleScore() {
        int highScore = Score.getHighScore(difficulty);
        int score = getScore();
        if (score > highScore) {
            Score.setHighScore(difficulty, score);
        }
    }
}
