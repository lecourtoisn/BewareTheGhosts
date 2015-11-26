package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BTGGame extends Game {
    private SimpleMenu simpleMenu;

	@Override
	public void create() {
        /*new ScreenListener(100) {
            public WorldEntity garry;
            public Widget pause;

            public void start() {
                //this.garry = new TestNexEntity(new World(screen.getWidth(), screen.getHeight()));
                this.garry = new Arrow(new World(screen.getWidth(), screen.getHeight()), Direction.DOWN);
                this.pause = new Widget(new Texture("ghost.png"), new Vector2(10, 10));
                garry.setPosition(5, 5);

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
        }.start();*/

        Score.setHighScore(0);
        simpleMenu = new SimpleMenu(this);
        launchSimpleMenu();
	}

    public void startGameSession() {
        GameSession gameSession = new GameSession(this);
        gameSession.start();
    }

    public void launchSimpleMenu() {
        simpleMenu.start();
    }
}
