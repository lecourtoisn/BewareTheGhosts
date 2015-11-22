package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.commandhandlers.InputHandler;
import com.mygdx.event.GhostAttack;
import com.mygdx.event.GhostSalvo;
import com.mygdx.event.IEvent;
import com.mygdx.screen.ScreenListener;
import com.mygdx.screen.UnStretchedScreen;
import com.mygdx.util.ScaledBitmapFont;
import com.mygdx.world.World;

public class GameSession extends ScreenListener {
    private static final float WORLD_HEIGHT = 100;

    private IEvent event;
    private UnStretchedScreen gameScreen;
    private BTGGame game;
    private World world;

    private ScaledBitmapFont font;

    public GameSession(BTGGame game) {
        this.game = game;
        gameScreen = new UnStretchedScreen(this, WORLD_HEIGHT);
        world = new World(gameScreen.getWidth(), gameScreen.getHeight());
        event = new GhostSalvo(world, 3, true, 1000, 20, 1000);

        font = new ScaledBitmapFont("fonts/calibri.ttf", WORLD_HEIGHT, 5);
        font.setColor(Color.RED);

        gameScreen.setInputProcessor(new InputHandler(world));
    }

    public void start() {
        game.setScreen(gameScreen);
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
    }

    public int getScore() {
        int score = 0;
        for (IEvent event : world.getEvents().getElements(GhostAttack.class)) {
            GhostAttack attack = (GhostAttack) event;
            score += attack.isOver() ? 1 : 0;
        }
        return score;
    }
}
