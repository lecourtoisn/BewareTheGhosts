package refact;

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

import static com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import static refact.BTGGame.assets;
import static refact.BTGGame.game;

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
        assets.load("loadingFont.ttf", BitmapFont.class, FontParam.build(Font.KENVECTORBOLD, 100, Color.WHITE));
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
                    assets.load("menuTitle.ttf", BitmapFont.class, FontParam.build(Font.KENVECTORBOLD, 70, Color.FOREST));
                    assets.load("menuButtons.ttf", BitmapFont.class, FontParam.build(Font.KENVECTOR, 65, Color.CORAL));
                    assets.load("menuButtonsForest.ttf", BitmapFont.class, FontParam.build(Font.KENVECTOR, 65, Color.FOREST));
                    assets.load("tokenQuantity.ttf", BitmapFont.class, FontParam.build(Font.KENVECTOR, 30, Color.CORAL));
                    assets.load("tokenCountdown.ttf", BitmapFont.class, FontParam.build(Font.KENVECTOR, 15, Color.CORAL));
                    assets.load("scoreLabelFont.ttf", BitmapFont.class, FontParam.build(Font.CALIBRI, 100, Color.BLACK));
                    assets.load("countdownLabelFont.ttf", BitmapFont.class, FontParam.build(Font.CALIBRIBOLD, 300, Color.BLACK));
                    assets.load("pauseLabel.ttf", BitmapFont.class, FontParam.build(Font.CALIBRIBOLD, 200, Color.WHITE));
                    assets.load("resumeLabel.ttf", BitmapFont.class, FontParam.build(Font.CALIBRIBOLD, 80, Color.WHITE));
                    assets.load("finalScoreLabel.ttf", BitmapFont.class, FontParam.build(Font.CALIBRIBOLD, 150, Color.WHITE));
                    step = STEP.FONTGEN;
                    break;
                case FONTGEN:
                    oMap.put("menuTitle", assets.get("menuTitle.ttf"));
                    oMap.put("menuButtons", assets.get("menuButtons.ttf"));
                    oMap.put("menuButtonsForest", assets.get("menuButtonsForest.ttf"));
                    oMap.put("tokenQuantity", assets.get("tokenQuantity.ttf"));
                    oMap.put("tokenCountdown", assets.get("tokenCountdown.ttf"));
                    oMap.put("scoreLabelFont", assets.get("scoreLabelFont.ttf"));
                    oMap.put("countdownLabelFont", assets.get("countdownLabelFont.ttf"));
                    oMap.put("pauseLabel", assets.get("pauseLabel.ttf"));
                    oMap.put("resumeLabel", assets.get("resumeLabel.ttf"));
                    oMap.put("finalScoreLabel", assets.get("finalScoreLabel.ttf"));
                    assets.load("textures/textures.json", Skin.class, new SkinParameter("textures/textures.atlas", oMap));
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
