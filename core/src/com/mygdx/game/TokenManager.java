package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.util.Hasher;

public class TokenManager {
    private static final int NB_TOKEN_MAX = 30;
    private static Preferences prefs = Gdx.app.getPreferences("token");
    private static long lastGain;
    private static int period = 30 * 1000;
//    private static int period = 5 * 60 * 1000;
    private static int nbToken;
    private static boolean paused;


    public static void initialize() {
        long defaultLg = System.currentTimeMillis();
        int defaultToken = 0;
        lastGain = prefs.getLong("lastGain", defaultLg);
        nbToken = prefs.getInteger("nbToken", defaultToken);
        validate(Hasher.hash(defaultLg), Hasher.hash(defaultToken));
    }

    public static void routine() {
        long currentTime = System.currentTimeMillis();
        if (isPaused()) {
            lastGain = currentTime;
        } else {
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
            System.out.println("J'ai " + nbToken + " tokens");
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

    public static int getRemainingSeconds() {
        return (int) ((lastGain+period-System.currentTimeMillis())/1000);
    }

    private static void save() {
        prefs.putLong("lastGain", lastGain);
        prefs.putLong("lastGain?", Hasher.hash(lastGain));
        prefs.putInteger("nbToken", nbToken);
        prefs.putLong("nbToken?", Hasher.hash(nbToken));
    }

    public static boolean hasMaxToken() {
        return NB_TOKEN_MAX <= nbToken;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        TokenManager.paused = paused;
    }
}
