package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.world.World;

public class TestNexEntity extends WorldEntity {
    private static final EntityInfo ei = EntityInfo.GARRY;

    public TestNexEntity(World world) {
        super(world, ei);
        setHitboxSize(ei.getHitbox());
        setOrigin(ei.getOrigin().x, ei.getOrigin().y);
        setPosition(50, 50);
        sprite.setColor(Color.MAGENTA);
    }
}
