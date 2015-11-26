package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.EntityInfo;

public class Label extends Widget {
    private ScaledBitmapFont font;

    public Label(ScaledBitmapFont font) {
        super(EntityInfo.GHOST.getTexture(), new Vector2(1, 1));
        this.font = font;
    }

    public void setText(String text) {
        font.setText(text);
        setGraphicSize(font.getSize().x, font.getSize().y);
    }

    @Override
    public void draw(Batch batch) {
        Rectangle bounds = new Rectangle(getGraphicBounds());
        float x = bounds.getX();
        float y = bounds.getY() + getGraphicSize().y;
        font.draw(batch, x, y);
    }


    /** Irrelevant methods **/
    @Override
    public void setTexture(Texture texture) {}
}
