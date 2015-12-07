package com.mygdx.userinterface.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.EntityInfo;
import com.mygdx.game.BTGGame;

public class PauseButton extends Widget {
    private static final TextureRegion TEXTURE = EntityInfo.PAUSEBUTTON.getTexture();
    private static final Vector2 GRAPHICSIZE = new Vector2(9, 9);
    private BTGGame game;
    public PauseButton(BTGGame game) {
        super(TEXTURE, GRAPHICSIZE);
        this.game = game;
        setTexture(TEXTURE);
    }

    @Override
    public void onTouched() {
        game.launchPauseMenu();
    }
}
