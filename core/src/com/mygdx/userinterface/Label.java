package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.ScaledBitmapFont;

public class Label extends UIElement {
    private ScaledBitmapFont font;

    public Label(UIManager uiManager, ScaledBitmapFont font) {
        super(uiManager);
        this.font = font;
    }

    public void setText(String text) {
        font.setText(text);
        setSize(font.getSize().x, font.getSize().y);
    }

    @Override
    public void draw(Batch batch) {
        Vector2 pos = getConcretePosition();
        pos.y += getSize().y;
        font.draw(batch, pos.x, pos.y);
    }
}
