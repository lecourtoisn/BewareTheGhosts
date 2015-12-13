package com.mygdx.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.util.writer.SmartFontGenerator;

public class FontGenerator {
    private static int[] heights = { 240, 320, 480, 640, 720, 800, 1080, 1440, 1600};

    public static void generate() {
        Gdx.files.local("fontLog").deleteDirectory();
        for (int height : heights) {
            generate(height);
        }
    }

    public static int getClosest() {
        int screenHeight = Gdx.graphics.getHeight();
        for (int i = heights.length; --i > 0; ) {
            if (screenHeight >= heights[i]) {
                return heights[i];
            }
        }
        return heights[0];
    }

    public static void generate(int height) {
        float ratio = height / 100f;
        SmartFontGenerator gen = new SmartFontGenerator();
        FileHandle originalSkin = Gdx.files.local("textures/textures.json");
        FileHandle skin = Gdx.files.local("fonts/generated/" + height + "/skin.json");
        originalSkin.copyTo(skin);
        gen.setForceGeneration(true);
        gen.setGeneratedFontDir("/fonts/generated/" + height + "/");
        gen.createFont(Gdx.files.internal(Font.KENVECTORBOLD), "title", (int) (12 * ratio), true, height);
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "mainLabel", (int) (12 * ratio), true, height);
        gen.createFont(Gdx.files.internal(Font.CALIBRIBOLD), "countdownLabel", (int) (53 * ratio), true, height);
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "tokenQuantity", (int) (5 * ratio), true, height);
        gen.createFont(Gdx.files.internal(Font.KENVECTOR), "tokenCountdown", (int) (3 * ratio), true, height);
        gen.createFont(Gdx.files.internal(Font.CALIBRI), "blackNWhiteLabel", (int) (20 * ratio), true, height);
        gen.setForceGeneration(false);
    }
}
