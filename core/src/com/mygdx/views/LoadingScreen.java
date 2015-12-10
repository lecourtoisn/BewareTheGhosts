package com.mygdx.views;

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
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.FontParam;
import com.mygdx.util.writer.FontCreation;

import static com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import static com.mygdx.game.BTGGame.assets;
import static com.mygdx.game.BTGGame.game;

public class LoadingScreen extends ScreenAdapter {
    private enum STEP {
        PRELOAD, FONTGEN, SKINLOAD
    }

    private Stage stage;
    private STEP step = STEP.PRELOAD;
    private ObjectMap<String, Object> oMap;

    public LoadingScreen() {
        oMap = new ObjectMap<String, Object>();
        stage = new Stage(new ScreenViewport());
        assets.load("loadingFont.ttf", BitmapFont.class, FontParam.build(Font.KENVECTORBOLD, 18, Color.WHITE));
        assets.finishLoading();
        oMap.put("loadingFont", assets.get("loadingFont.ttf"));
        assets.load("loadingScreen/loading.json", Skin.class, new SkinParameter(oMap));
        assets.finishLoading();

        Skin loadSkin = assets.get("loadingScreen/loading.json");

        Table table = new Table(loadSkin);
        table.setFillParent(true);

        Label loadingLabel = new Label("Loading", loadSkin, "loading");

        table.add(loadingLabel).expand().center();
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
                    FontCreation.FontCreationRoutine(FontParam.ratio);
                    /*assets.load("title.ttf", BitmapFont.class, FontParam.build(Font.KENVECTORBOLD, 12, Color.WHITE));
                    assets.load("mainLabel.ttf", BitmapFont.class, FontParam.build(Font.KENVECTOR, 12, Color.WHITE));
                    assets.load("countdownLabel.ttf", BitmapFont.class, FontParam.build(Font.CALIBRIBOLD, 53, Color.WHITE));
                    assets.load("tokenQuantity.ttf", BitmapFont.class, FontParam.build(Font.KENVECTOR, 5, Color.WHITE));
                    assets.load("tokenCountdown.ttf", BitmapFont.class, FontParam.build(Font.KENVECTOR, 3, Color.WHITE));
                    assets.load("blackNWhiteLabel.ttf", BitmapFont.class, FontParam.build(Font.CALIBRI, 20, Color.WHITE));*/
                    step = STEP.FONTGEN;
                    break;
                case FONTGEN:
                    /*oMap.put("title", assets.get("title.ttf"));
                    oMap.put("mainLabel", assets.get("mainLabel.ttf"));
                    oMap.put("countdownLabel", assets.get("countdownLabel.ttf"));
                    oMap.put("tokenQuantity", assets.get("tokenQuantity.ttf"));
                    oMap.put("tokenCountdown", assets.get("tokenCountdown.ttf"));
                    oMap.put("blackNWhiteLabel", assets.get("blackNWhiteLabel.ttf"));*/
                    assets.load("textures/textures.json", Skin.class, new SkinParameter("textures/textures.atlas"/*, oMap*/));
                    step = STEP.SKINLOAD;
                    break;
                case SKINLOAD:
                    // Everything has been loaded
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
