package com.mygdx.userinterface.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;

public class Widget extends Entity {
    public Widget(TextureRegion texture, Vector2 graphicSize) {
        super(texture, graphicSize);
    }

    @Override
    public void setGraphicSize(float x, float y) {
        Vector2 oldSize = graphicSize;
        graphicSize = new Vector2(x, y);
        origin.x *= graphicSize.x / oldSize.x;
        origin.y *= graphicSize.y / oldSize.y;
    }

    public void onTouched() {
        // Does nothing by default
    }
}
