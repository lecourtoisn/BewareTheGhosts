package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.util.Hasher;

public class TokenManager {
    public static final int NB_TOKEN_MAX = 15;
    private static Preferences prefs = Gdx.app.getPreferences("token");
    private static long lastGain;
//    private static int period = 20 * 1000;
    private static int period = 5 * 60 * 1000;
    private static int nbToken;
    private static boolean paused;

    private static long pauseSaving;

    public static void initialize() {
        long defaultLg = 0;
        int defaultToken = 0;
        lastGain = prefs.getLong("lastGain", defaultLg);
        nbToken = prefs.getInteger("nbToken", defaultToken);

        validate(Hasher.hash(defaultLg), Hasher.hash(defaultToken));
    }

    public static void routine(float delta) {
        long currentTime = System.currentTimeMillis();
        if (!isPaused()) {
            int nbNewToken = (int) ((currentTime - lastGain)/period);
            if (nbNewToken > 0) {
                incrementToken(nbNewToken);
            }
        }
    }

    public static void incrementToken(int nbNewToken) {
        if (!hasMaxToken()) {
            nbToken += nbNewToken;
            nbToken = nbToken > NB_TOKEN_MAX ? NB_TOKEN_MAX : nbToken;
            lastGain = System.currentTimeMillis();
        }
    }

    public static boolean hasToken() {
        return nbToken > 0;
    }

    public static void decrementToken() {
        nbToken -= nbToken <= 0 ? 0 : 1;
    }

    private static void validate(long defaultLG, long defaultToken) {
        long hashLG = prefs.getLong("lastGain?", defaultLG);
        long hashToken = prefs.getLong("nbToken?", defaultToken);
        if (Hasher.hash(lastGain) != hashLG) {
            lastGain = 0;
        }
        if (Hasher.hash(nbToken) != hashToken) {
            nbToken = 0;
        }
    }

    public static void save() {
        prefs.putLong("lastGain", lastGain);
        prefs.putLong("lastGain?", Hasher.hash(lastGain));
        prefs.putInteger("nbToken", nbToken);
        prefs.putLong("nbToken?", Hasher.hash(nbToken));
        prefs.flush();
    }

    public static boolean hasMaxToken() {
        return NB_TOKEN_MAX <= nbToken;
    }

    public static int getNbToken() {
        return nbToken;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        TokenManager.paused = paused;
        if (paused) {
            pauseSaving = getRemainingMilliseconds();
        } else {
            setRemainingMilliseconds(pauseSaving);
        }
    }

    public static long getRemainingMilliseconds() {
        return lastGain+period-System.currentTimeMillis();
    }

    public static int getRemainingSeconds() {
        return (int) (getRemainingMilliseconds()/1000);
    }

    public static String getRemainingSecondsStr() {
        if (hasMaxToken()) {
            return " ";
        }
        long remaining = getRemainingSeconds()+1;
        int minutes = (int) remaining / 60;
        int seconds = (int) remaining % 60;
        return minutes + ":" + seconds;
    }

    public static void setRemainingMilliseconds(long remainingMilliseconds) {
        lastGain = System.currentTimeMillis() - period + remainingMilliseconds;
    }
}
