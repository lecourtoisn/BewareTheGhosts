package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.commandhandlers.InputHandler;
import com.mygdx.event.GhostSalvo;
import com.mygdx.event.IEvent;
import com.mygdx.screen.GameScreen;
import com.mygdx.world.World;

public class GameSession {
    private IEvent event;
    private Screen gameScreen;
    private BTGGame game;
    private World world;

    public GameSession(BTGGame game) {
        this.game = game;
        world = new World();
        event = new GhostSalvo(world, 3, true, 1000, 10, 1000);
        gameScreen = new GameScreen(this, new InputHandler(world), world.WIDTH, world.HEIGHT);
    }

    public void start() {
        game.setScreen(gameScreen);
        event.start();
    }

    public void update(float delta) {
        world.update(delta);
        if (event.isOver()) {
            game.launchSimpleMenu();
        }
    }

    public void render(SpriteBatch batch, OrthographicCamera cam) {
        world.render(batch, cam);
    }
}
