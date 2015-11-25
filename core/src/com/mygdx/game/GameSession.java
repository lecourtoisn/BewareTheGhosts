package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.commandhandlers.GameSessionInputHandler;
import com.mygdx.event.EndlessSalvos;
import com.mygdx.event.IEvent;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.Label;
import com.mygdx.userinterface.PauseButton;
import com.mygdx.util.ScaledBitmapFont;
import com.mygdx.world.World;

public class GameSession extends ScreenListener {
    private static final float WORLD_HEIGHT = 100;

    private IEvent event;
    private BTGGame game;
    private World world;

    private ScaledBitmapFont font;

    // UI part
    private PauseButton pauseButton;
    private Label scoreLabel;
    private PauseMenu pauseMenu;

    public GameSession(BTGGame game) {
        super(WORLD_HEIGHT);
        this.game = game;
        world = new World(screen.getWidth(), screen.getHeight());
        event = new EndlessSalvos(world);
        pauseMenu = new PauseMenu(game, this);

        screen.setInputProcessor(new GameSessionInputHandler(screen, world, manager));
        initializeUI();
    }

    private void initializeUI() {
        /**/font = new ScaledBitmapFont("fonts/calibrib", WORLD_HEIGHT, 10);
        /**/font.setColor(Color.BLACK);
        pauseButton = new PauseButton(manager, this);
        pauseButton.setSize(9, 9);
        pauseButton.setPosition(screen.getWidth() - 5, screen.getHeight() - 5);

        scoreLabel = new Label(manager, font);
        scoreLabel.setPosition(screen.getWidth() / 2, screen.getHeight() - 5);
    }

    public void start() {
        game.setScreen(screen);
        event.start();
    }

    @Override
    public void update(float delta) {
        world.update(delta);
        scoreLabel.setText(String.valueOf(getScore()));
        if (event.isOver()) {
            int highScore = Score.getHighScore();
            int score = getScore();
            if (score > highScore) {
                Score.setHighScore(getScore());
            }
            game.launchSimpleMenu();
        }
    }

    @Override
    public void render(Batch batch, Camera cam) {
        world.render(batch, cam);
        //font.draw(batch, String.valueOf(getScore()), 10, WORLD_HEIGHT - 10);
        scoreLabel.draw(batch);
        pauseButton.draw(batch);
    }

    public int getScore() {
        return world.getGarry().getAttackAvoided();
    }

    public void launchPauseMenu() {
        // Since the screen will be changed no need to pause anything i think
        pauseMenu.start();
    }

    public void resumeGame() {
        game.setScreen(screen);
    }
}
