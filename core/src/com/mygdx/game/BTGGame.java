package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.entity.TestNexEntity;
import com.mygdx.entity.WorldEntity;
import com.mygdx.screen.ScreenListener;
import com.mygdx.world.World;

public class BTGGame extends Game {
    private SimpleMenu simpleMenu;

	@Override
	public void create() {
        new ScreenListener(100) {
            public WorldEntity garry;

            public void start() {
                this.garry = new TestNexEntity(new World(screen.getWidth(), screen.getHeight()));
                garry.setOrigin(0, 0);
                garry.setPosition(0, 0);
                System.out.println("Graphic " + garry.getGraphicBounds());
                System.out.println("Hitbox " + garry.getHitbox());
                setScreen(screen);
            }

            @Override
            public void update(float delta) {
                this.garry.update(delta);
            }

            @Override
            public void render(Batch batch, Camera cam) {
                this.garry.draw(batch);
            }
        }.start();

        /*Score.setHighScore(0);
        simpleMenu = new SimpleMenu(this);
        launchSimpleMenu();*/
	}

    public void startGameSession() {
        GameSession gameSession = new GameSession(this);
        gameSession.start();
    }

    public void launchSimpleMenu() {
        simpleMenu.start();
    }
}
