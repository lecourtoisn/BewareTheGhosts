package com.mygdx.util.writer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.userinterface.elements.Font;

public class FontCreation {
    private FreeTypeFontGenerator generator;
    public static final int HARD_CODED_VERSION = 1;

    public static void FontCreationRoutine(float ratio) {
        Preferences prefs = Gdx.app.getPreferences("fontGen");
        boolean forceGeneration = HARD_CODED_VERSION != prefs.getInteger("version", 0);
        SmartFontGenerator gen = new SmartFontGenerator();
        gen.setForceGeneration(forceGeneration);
        gen.setGeneratedFontDir("/fonts/generated/");
        gen.createFont(Gdx.files.internal(Font.KENVECTORBOLD), "title", (int) (12 * ratio));
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "mainLabel", (int) (12 * ratio));
        gen.createFont(Gdx.files.internal(Font.CALIBRIBOLD), "countdownLabel", (int) (53 * ratio));
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "tokenQuantity", (int) (5 * ratio));
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "tokenCountdown", (int) (3 * ratio));
        gen.createFont(Gdx.files.internal(Font.CALIBRI), "blackNWhiteLabel", (int) (20 * ratio));
        gen.setForceGeneration(false);
        prefs.putInteger("version", HARD_CODED_VERSION);
        prefs.flush();
    }

}
