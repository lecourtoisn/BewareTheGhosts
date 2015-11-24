package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class UIElement implements IUIElement {
    private Vector2 position;
    private Sprite sprite;

    public UIElement(UIManager uiManager) {
        position = new Vector2();
        sprite = new Sprite();
        uiManager.addElement(this);
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public void setCenterPosition(float x, float y) {
        sprite.setCenter(x, y);
        this.position.x = sprite.getX();
        this.position.y = sprite.getY();
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setSize(float width, float height) {
        sprite.setSize(width, height);
    }

    @Override
    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public void update(float delta) {
        // Does nothing by default
    }

    @Override
    public Rectangle getHitbox() {
        return sprite.getBoundingRectangle();
    }

    protected void setTexture(Texture texture) {
        sprite.setRegion(texture);
    }
}
