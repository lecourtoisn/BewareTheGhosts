package com.mygdx.userinterface.elements;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.EntityInfo;

public class Label extends Widget {
    private ScaledBitmapFont font;

    public Label(FileHandle font, float viewportHeight, int fontSize) {
        super(EntityInfo.GHOST.getTexture(), new Vector2(1, 1));
        this.font = new ScaledBitmapFont(font, viewportHeight, fontSize);
    }

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

    public void setColor(Color color) {
        font.setColor(color);
    }


    /** Irrelevant methods
     * @param texture**/
    @Override
    public void setTexture(TextureRegion texture) {}
}
