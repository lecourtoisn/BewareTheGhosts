package com.mygdx.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.commandhandlers.GameViewInput;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.event.EndlessSalvos;
import com.mygdx.event.IEvent;
import com.mygdx.game.BTGGame;
import com.mygdx.game.ScoreManager;
import com.mygdx.game.TokenManager;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;
import com.mygdx.userinterface.elements.PauseButton;
import com.mygdx.util.CountDown;
import com.mygdx.util.International;
import com.mygdx.world.World;

import static com.mygdx.util.International.Label.TOUCH;

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

        screen.setInputProcessor(new GameViewInput(screen, world, manager, countDown).getDetector());
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
        TokenManager.setPaused(true);
    }

    @Override
    public void hide() {
        TokenManager.setPaused(false);
    }

    @Override
    public void pause() {
        pauseButton.onTouched();
    }

    @Override
    public void update(float delta) {
        countDown.update(delta);
        if (countDown.isOver()) {
            manager.removeElement(countDownLabel);
            world.update(delta);
            scoreLabel.setText(String.valueOf(getScore()));
            if (event.isOver()) {
                int highScore = ScoreManager.getHighScore(difficulty);
                int score = getScore();
                boolean isHighScore = score > highScore;
                handleScore(score, isHighScore);
                game.launchEndOfGameView(difficulty, score, isHighScore);
            }
        } else {
            if (countDown.isRunning()) {
                countDownLabel.setText(countDown.getSecondStr());
            } else {
                countDownLabel.setText(International.get(TOUCH));
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

    private void handleScore(int score, boolean isHighScore) {
        if (isHighScore) {
            ScoreManager.setHighScore(difficulty, score);
        }
    }
}
