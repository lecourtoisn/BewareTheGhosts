package refact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.util.International;

import static com.mygdx.event.DifficultySchema.Difficulty.HARD;
import static com.mygdx.event.DifficultySchema.Difficulty.NORMAL;
import static com.mygdx.game.ScoreManager.getHighScore;
import static refact.BTGGame.assets;
import static refact.BTGGame.game;

public class HighScoreView extends ScreenAdapter{
    private Stage stage;
    private Label normalScore;
    private Label hardScore;
    public HighScoreView() {
        stage = new Stage(new ScreenViewport());
        Skin skin = assets.get("textures/textures.json");

        Table root = new Table();
        root.setSkin(skin);
        root.setFillParent(true);
        root.setBackground("background");

        int pad = 50;

        Label title = new Label(International.get(International.Label.HIGHSCORE), skin, "title");
        Label normal = new Label(International.get(International.Label.NORMALLBL), skin, "buttonStyle");
        Label hard = new Label(International.get(International.Label.HARDLBL), skin, "buttonStyle");
        normalScore = new Label("0", skin, "buttonStyle");
        hardScore = new Label("0", skin, "buttonStyle");

        Container<Label> titleCnt = new Container<Label>(title).top().left();
        root.add(titleCnt).expand().top().left().pad(pad, pad, 0, 0);

        VerticalGroup labels = new VerticalGroup().left().padRight(20);
        labels.addActor(normal);
        labels.addActor(hard);

        VerticalGroup scores = new VerticalGroup().left();
        scores.addActor(normalScore);
        scores.addActor(hardScore);

        root.row();

        HorizontalGroup hg = new HorizontalGroup().bottom().pad(0, pad, pad, 0);
        hg.addActor(labels);
        hg.addActor(scores);

        root.add(hg).left();

        stage.addActor(root);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.launchMainView();
            }
        });
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
        normalScore.setText(String.valueOf(getHighScore(NORMAL)));
        hardScore.setText(String.valueOf(getHighScore(HARD)));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
