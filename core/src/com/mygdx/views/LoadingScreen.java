package com.mygdx.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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

import static com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import static com.mygdx.game.BTGGame.*;

public class LoadingScreen extends ScreenAdapter {
    private enum STEP {
        PRELOAD, FONTGEN, SKINLOAD
    }

    private Stage stage;
    private STEP step = STEP.PRELOAD;
    private ObjectMap<String, Object> oMap;
    private String skinPath;

    public LoadingScreen() {
        skinPath = "fonts/generated/"+ FontGenerator.getClosest()+"/skin.json";
//        oMap = new ObjectMap<String, Object>();
        stage = new Stage(new ScreenViewport());
        //assets.load("loadingFont.ttf", BitmapFont.class, FontParam.build(Font.KENVECTORBOLD, 18, Color.WHITE));
        //assets.finishLoading();
        //oMap.put("loadingFont", assets.get("loadingFont.ttf"));
        //assets.load("loadingScreen/loading.json", Skin.class, new SkinParameter(oMap));
        //assets.finishLoading();

//        Skin loadSkin = assets.get("loadingScreen/loading.json");

        Table table = new Table();
        table.setFillParent(true);

        BitmapFont loadingFont = new BitmapFont(Gdx.files.internal("fonts/loadingFont.fnt"));
        Label loadingLabel = new Label("Loading", new Label.LabelStyle(loadingFont, Color.WHITE));
        table.add(loadingLabel).expand().center();
        table.row();
        stage.addActor(table);
    }

    @Override
    public void resize(int width, int height) {
        Viewport viewport = stage.getViewport();
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        if (assets.update()) {
            switch (step) {
                case PRELOAD:
                    step = STEP.FONTGEN;
                    break;
                case FONTGEN:
                    assets.load(skinPath, Skin.class, new SkinParameter("textures/textures.atlas"));
                    step = STEP.SKINLOAD;
                    break;
                case SKINLOAD:
                    // Everything has been loaded
                    skin = assets.get(skinPath);
                    game.instantiateViews();
                    game.launchMainView();
                    break;
            }
        }
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
