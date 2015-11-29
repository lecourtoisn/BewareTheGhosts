package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.util.Randomizer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum EntityInfo {
    GARRY(new Texture("garry.png"), 6.2f, 9),
    ARROW(new Texture("arrow.png"), 10, 10),
    GHOST(new Texture("ghost.png"), 6.2f, 9),
    CLEAR(new Texture("clear.png"), 1, 1);

    static {
        float defaultHitbox = 4.5f;
        Set<EntityInfo> infoSet = new HashSet<EntityInfo>(Arrays.asList(GARRY, GHOST));
        for (EntityInfo info : infoSet) {
            info.setHitbox(defaultHitbox, defaultHitbox);
        }
    }

    private Texture texture;
    private Vector2 size;
    private Vector2 hitbox;

    EntityInfo(Texture texture, float sizeW, float sizeH, float hbW, float hbH) {
        this.texture = texture;
        this.size = new Vector2(sizeW, sizeH);
        this.hitbox = new Vector2(hbW, hbH);
    }

    EntityInfo(Texture texture, float sizeW, float sizeH) {
        this(texture, sizeW, sizeH, sizeW, sizeH);
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

    public Vector2 getOrigin() {
        return new Vector2(size.x/2f, size.y/2f);
    }

    public Vector2 getHitbox() {
        return hitbox;
    }


    // Only to bypass the fact that java doesn't allow static attribute to be passed in constructor
    private void setHitbox(float width, float height) {
        hitbox = new Vector2(width, height);
    }
}
