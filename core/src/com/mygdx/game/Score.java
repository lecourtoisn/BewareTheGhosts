package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Score {
    private static Preferences prefs = Gdx.app.getPreferences("scores");

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void setHighScore(int highScore) {
        prefs.putInteger("highScore", highScore);
        prefs.flush();
    }
}
