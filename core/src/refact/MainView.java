package refact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.util.International;

import static refact.BTGGame.assets;
import static refact.BTGGame.game;

public class MainView extends ScreenAdapter {
    private Stage stage;
    public MainView() {
//        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
//        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage = new Stage(new ScreenViewport());

        Skin skin = assets.get("textures/textures.json");

        Table root = new Table();
        root.setSkin(skin);
        root.setFillParent(true);
        root.setBackground("background");

        int pad = 50;

        Label title = new Label(International.get(International.Label.TITLE), skin, "title");
        Label normal = new Label(International.get(International.Label.NORMALLBL), skin, "buttonStyle");
        Label hard = new Label(International.get(International.Label.HARDLBL), skin, "buttonStyle");
        Label highScore = new Label(International.get(International.Label.HIGHSCORE), skin, "buttonStyle");

        Container<Label> titleCnt = new Container<Label>(title).top().left();
        root.add(titleCnt).expand().top().left().pad(pad, pad, 0, 0);

        VerticalGroup buttons = new VerticalGroup().left().padRight(20);
        buttons.addActor(normal);
        buttons.addActor(hard);
        buttons.addActor(highScore);

        root.row();

        HorizontalGroup hg = new HorizontalGroup().bottom().pad(0, pad, pad, 0);
        hg.addActor(buttons);
        Image garry = new Image(skin.getDrawable("garry"));
        garry.setScale(1.5f);
        hg.addActor(garry);

        root.add(hg).left();

        highScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.launchHighScoreView();
            }
        });
        stage.addActor(root);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
