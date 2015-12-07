package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BTGGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Beware The ghosts !";
        config.width = 1000; config.height = 563;
//        config.width = 2560; config.height = 1440;
		new LwjglApplication(new BTGGame(), config);
	}
}
