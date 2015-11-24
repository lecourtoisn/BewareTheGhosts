package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.commandhandlers.GameSessionInputHandler;
import com.mygdx.event.EndlessSalvos;
import com.mygdx.event.IEvent;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.PauseButton;
import com.mygdx.userinterface.UIElement;
import com.mygdx.userinterface.UIManager;
import com.mygdx.util.ScaledBitmapFont;
import com.mygdx.world.World;

public class GameSession extends ScreenListener {
    private static final float WORLD_HEIGHT = 100;

    private IEvent event;
    private BTGGame game;
    private World world;

    private ScaledBitmapFont font;

    // UI part
    private UIManager manager;
    private UIElement pauseButton;

    public GameSession(BTGGame game) {
        super(WORLD_HEIGHT);
        this.game = game;
        world = new World(screen.getWidth(), screen.getHeight());
        event = new EndlessSalvos(world);
        manager = new UIManager();

        screen.setInputProcessor(new GameSessionInputHandler(screen, world, manager));
        initializeUI();
    }

    private void initializeUI() {
        /**/font = new ScaledBitmapFont("fonts/calibri.ttf", WORLD_HEIGHT, 10);
        /**/font.setColor(Color.GOLD);
        pauseButton = new PauseButton(manager);
        pauseButton.setSize(9, 9);
        pauseButton.setCenterPosition(screen.getWidth() - 5, screen.getHeight() - 5);
    }

    public void start() {
        game.setScreen(screen);
        event.start();
    }

    @Override
    public void update(float delta) {
        world.update(delta);
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
        font.draw(batch, String.valueOf(getScore()), 10, WORLD_HEIGHT - 10);
        pauseButton.draw(batch);
    }

    public int getScore() {
        return world.getGarry().getAttackAvoided();
    }
}
