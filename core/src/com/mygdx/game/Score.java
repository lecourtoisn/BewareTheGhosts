package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.event.DifficultySchema.Difficulty;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Score {
    private static MessageDigest md;
    private static Preferences prefs = Gdx.app.getPreferences("scores");

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException ignored) {}
    }

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
        prefs.putLong(difficulty.name() + "?", hash(highScore));
    }

    private static long hash(int highScore) {
        byte[] bytes = ByteBuffer.allocate(4).putInt(highScore).array();
        md.update(bytes);
        byte[] digest = md.digest();
        ByteBuffer bb = ByteBuffer.wrap(digest);
        return bb.getLong();
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
            if (hash != hash(score)) {
                setHighScore(difficulty, 0);
            }
        }
    }
}
