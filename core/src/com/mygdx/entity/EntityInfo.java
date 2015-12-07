package com.mygdx.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.util.Randomizer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.mygdx.game.BTGGame.assets;

public enum EntityInfo {
    GARRY("garry", 6.2f, 9),
    ARROW("arrow", 10, 10),
    GHOST("ghost", 6.2f, 9),
    PAUSE("pause", 10, 10),
    BACKGROUND("background", 1, 1),
    GRID("grid", 1, 1);

    static {
        float defaultHitbox = 4.5f;
        Set<EntityInfo> infoSet = new HashSet<EntityInfo>(Arrays.asList(GARRY, GHOST));
        for (EntityInfo info : infoSet) {
            info.setHitbox(defaultHitbox, defaultHitbox);
        }
    }

    private String texture;
    private Vector2 size;
    private Vector2 hitbox;

    EntityInfo(String texture, float sizeW, float sizeH, float hbW, float hbH) {
        this.texture = texture;
        this.size = new Vector2(sizeW, sizeH);
        this.hitbox = new Vector2(hbW, hbH);
    }

    EntityInfo(String texture, float sizeW, float sizeH) {
        this(texture, sizeW, sizeH, sizeW, sizeH);
    }

    public static EntityInfo getRandom() {
        EntityInfo[] values = EntityInfo.values();
        return values[Randomizer.getInt(values.length)];
    }

    public TextureRegion getTexture() {
        Skin skin = assets.get("textures/textures.json");
        return skin.getRegion(texture);
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
