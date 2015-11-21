package com.mygdx.game;

import com.badlogic.gdx.Screen;
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
        event = new GhostSalvo(world, 3, true, 1000, 20, 1000);
        gameScreen = new GameScreen(world);
    }

    public void start() {
        game.setScreen(gameScreen);
        event.start();
    }
}
