package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.screen.GameScreen;

public class BTGGame extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
}
