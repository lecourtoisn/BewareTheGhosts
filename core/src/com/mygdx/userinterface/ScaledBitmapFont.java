package com.mygdx.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;

public class ScaledBitmapFont {
    private BitmapFont bitmapFont;
    private GlyphLayout glyphLayout;

    public ScaledBitmapFont(String path, float viewportHeight, float fontSize) {
        float scale = Gdx.graphics.getHeight() / viewportHeight;
        path = path.concat(".ttf");
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(path));

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = (int) (fontSize * scale);
        bitmapFont = fontGenerator.generateFont(parameter);
        bitmapFont.getData().setScale(1f / scale);
        glyphLayout = new GlyphLayout();
    }

    public void setText(String text) {
        glyphLayout.reset();
        glyphLayout.setText(bitmapFont, text);
    }

    public Vector2 getSize() {
        return new Vector2(glyphLayout.width, glyphLayout.height);
    }

    public void draw(Batch batch, float x, float y) {
        bitmapFont.draw(batch, glyphLayout, x, y);
    }

/*
    public void drawCenter(Batch batch,  float x, float y) {
        Vector2 center = new Vector2(x, y);
        center.sub(glyphLayout.width/2, glyphLayout.height/-2);
        bitmapFont.draw(batch, glyphLayout, center.x, center.y);
    }
*/

    public void setColor(Color color) {
        bitmapFont.setColor(color);
    }
}
