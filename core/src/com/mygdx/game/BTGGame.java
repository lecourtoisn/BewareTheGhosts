package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.mygdx.userinterface.elements.FontParam;
import com.mygdx.util.writer.FontCreation;

public class BTGGame implements ApplicationListener {
    public static final AssetManager assets = new AssetManager();
    public static SpriteBatch spriteBatch;
    public static final MainGame game = new MainGame();

    @Override
    public void create() {
        FontParam.ratio = Gdx.graphics.getHeight()/100f;
        InternalFileHandleResolver resolver = new InternalFileHandleResolver();
        assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        Texture.setAssetManager(assets);

        spriteBatch = new SpriteBatch();

        TokenManager.initialize();
        ScoreManager.antiCheatRoutine();

        game.create();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        TokenManager.routine(Gdx.graphics.getDeltaTime());
        game.render();
    }

    @Override
    public void pause() {
        TokenManager.save();
        game.pause();
    }

    @Override
    public void resume() {
        game.resume();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
