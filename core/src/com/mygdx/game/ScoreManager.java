package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.util.Hasher;

public class ScoreManager {
    private static Preferences prefs = Gdx.app.getPreferences("scores");

    public static int getHighScore(Difficulty difficulty) {
        return prefs.getInteger(difficulty.name(), 0);
    }

    private static long getHash(Difficulty difficulty) {
        return prefs.getLong(difficulty.name() + "?", 0);
    }

    public static void setHighScore(Difficulty difficulty, int highScore) {
        prefs.putInteger(difficulty.name(), highScore);
        setHash(difficulty, highScore);
        prefs.flush();
    }

    private static void setHash(Difficulty difficulty, int highScore) {
        prefs.putLong(difficulty.name() + "?", Hasher.hash(highScore));
    }

    public static void resetScores() {
        for (Difficulty difficulty : Difficulty.values()) {
            setHighScore(difficulty, 0);
        }
    }

    public static void antiCheatRoutine() {
        // Routing determining whether the user has cheated and modified its score
        for (Difficulty difficulty : Difficulty.values()) {
            int score = getHighScore(difficulty);
            long hash = getHash(difficulty);
            if (hash != Hasher.hash(score)) {
                setHighScore(difficulty, 0);
            }
        }
    }
}
