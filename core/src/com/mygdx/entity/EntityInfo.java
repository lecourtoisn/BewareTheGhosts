package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.Randomizer;

public enum EntityInfo {
    GARRY(new Texture("garry.png"), 6.2f, 9),
    ARROW(new Texture("arrow.png"), 10, 10),
    GHOST(new Texture("ghost.png"), 6.2f, 9),
    SUN(new Texture("sun1.png"), 9, 9),
    SPIDER(new Texture("spider.png"), 9, 5.7f),
    MOUSE(new Texture("mouse.png"), 9, 5.3f),
    SPIKEMAN(new Texture("spikeMan_stand.png"), 5.3f, 9);

    private Texture texture;
    private Vector2 size;

    EntityInfo(Texture texture, float width, float height) {
        this.texture = texture;
        this.size = new Vector2(width, height);
    }

    public static EntityInfo getRandom() {
        EntityInfo[] values = EntityInfo.values();
        return values[Randomizer.getInt(values.length)];
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getSize() {
        return size;
    }

    public Vector2 getCenter() {
        return new Vector2(size.x/2f, size.y/2f);
    }
}
