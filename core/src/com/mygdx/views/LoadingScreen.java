package com.mygdx.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.util.FontGenerator;

import static com.mygdx.game.BTGGame.*;

public class LoadingScreen extends ScreenAdapter {
    private Stage stage;
    private ObjectMap<String, Object> oMap;
    private String skinPath;

    public LoadingScreen() {
        skinPath = "fonts/generated/"+ FontGenerator.getClosest()+"/skin.json";
        stage = new Stage(new ScreenViewport());

        Table table = new Table();
        table.setFillParent(true);

        BitmapFont loadingFont = new BitmapFont(Gdx.files.internal("fonts/loadingFont.fnt"));
        Label loadingLabel = new Label("Loading", new Label.LabelStyle(loadingFont, Color.WHITE));
        table.add(loadingLabel).expand().center();
        table.row();
        stage.addActor(table);

        assets.load(skinPath, Skin.class, new SkinLoader.SkinParameter("textures/textures.atlas"));
    }

    @Override
    public void resize(int width, int height) {
        Viewport viewport = stage.getViewport();
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        if (assets.update()) {
            // Everything has been loaded
            skin = assets.get(skinPath);
            game.instantiateViews();
            game.launchMainView();
        }
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
