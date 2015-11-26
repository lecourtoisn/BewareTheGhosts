package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.event.DifficultySchema.*;

public class Score {
    private static Preferences prefs = Gdx.app.getPreferences("scores");

    public static int getHighScore(Difficulty difficulty) {
        return prefs.getInteger(difficulty.name());
    }

    public static void setHighScore(Difficulty difficulty, int highScore) {
        prefs.putInteger(difficulty.name(), highScore);
        prefs.flush();
    }

    public static void resetScores() {
        for (Difficulty difficulty : Difficulty.values()) {
            setHighScore(difficulty, 0);
        }
    }
}
