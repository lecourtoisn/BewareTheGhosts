/*
package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class UIElement implements IUIElement {
    private Vector2 position;
    private Sprite sprite;
    private Vector2 size;
    private Vector2 center;

    protected boolean centered;
    public UIElement(WidgetManager widgetManager) {
        center = new Vector2();
        position = new Vector2();
        sprite = new Sprite();
        size = new Vector2();
        widgetManager.addElement(this);
        setCentered(true);
    }

    @Override
    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public void setCentered(boolean centered) {
        this.centered = centered;
        center = centered ? getSize().scl(0.5f) : new Vector2(0, 0);
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(position);
    }

    @Override
    public void setSize(float width, float height) {
        size = new Vector2(width, height);
        setCentered(centered);
    }

    public Vector2 getSize() {
        return new Vector2(size);
    }

    @Override
    public void draw(Batch batch) {
        Vector2 pos = getConcretePosition();
        sprite.setPosition(pos.x, pos.y);
        sprite.setSize(size.x, size.y);
        sprite.draw(batch);
    }

    @Override
    public void update(float delta) {
        // Does nothing by default
    }

    @Override
    public Rectangle getHitbox() {
        Rectangle hitbox = new Rectangle();
        Vector2 hbSize = getSize();
        hitbox.setSize(hbSize.x, hbSize.y);
        hitbox.setPosition(getConcretePosition());
        return hitbox;
    }

    @Override
    public void onTouched() {
        // Does nothing by default
    }

    protected void setTexture(Texture texture) {
        sprite.setRegion(texture);
    }

    protected Vector2 getConcretePosition() {
        Vector2 pos = getPosition();
        pos.sub(getCenter());
        return pos;
    }

    public Vector2 getCenter() {
        return new Vector2(center);
    }
}
*/
