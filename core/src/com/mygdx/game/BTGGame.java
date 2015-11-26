package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.TestNexEntity;
import com.mygdx.entity.Widget;
import com.mygdx.entity.WorldEntity;
import com.mygdx.screen.ScreenListener;
import com.mygdx.world.World;

public class BTGGame extends Game {
    private SimpleMenu simpleMenu;

	@Override
	public void create() {
        new ScreenListener(100) {
            public WorldEntity garry;
            public Widget pause;

            public void start() {
                this.garry = new TestNexEntity(new World(screen.getWidth(), screen.getHeight()));
                this.pause = new Widget(new Texture("ghost.png"), new Vector2(10, 10));
                garry.setPosition(3.1f, 4.5f);

                pause.setOrigin(10, 10);
                pause.setPosition(screen.getWidth(), screen.getHeight());
                pause.setGraphicSize(20, 20);

                setScreen(screen);
            }

            @Override
            public void update(float delta) {
                garry.update(delta);
                //pause.update(delta);
            }

            @Override
            public void render(Batch batch, Camera cam) {
                garry.draw(batch);
                pause.draw(batch);
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
