package com.mygdx.util.writer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.userinterface.elements.Font;

public class FontCreation {
    private FreeTypeFontGenerator generator;

    public static void FontCreationRoutine(float ratio) {
        SmartFontGenerator gen = new SmartFontGenerator();
        gen.setGeneratedFontDir("/fonts/generated/");
        gen.createFont(Gdx.files.internal(Font.KENVECTORBOLD), "title", (int) (12 * ratio));
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "mainLabel", (int) (12 * ratio));
        gen.createFont(Gdx.files.internal(Font.CALIBRIBOLD), "countdownLabel", (int) (53 * ratio));
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "tokenQuantity", (int) (5 * ratio));
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "tokenCountdown", (int) (3 * ratio));
        gen.createFont(Gdx.files.internal(Font.CALIBRI), "blackNWhiteLabel", (int) (20 * ratio));
    }
}
