package refact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.event.EndlessSalvos;
import com.mygdx.event.IEvent;
import com.mygdx.game.ScoreManager;
import com.mygdx.util.CountDown;
import com.mygdx.util.International;
import com.mygdx.world.World;

import static com.mygdx.util.International.Label.TOUCH;
import static refact.BTGGame.*;

public class GameView extends ScreenAdapter {
    private final float WORLD_HEIGHT = 100;
    private IEvent event;
    private Difficulty difficulty;
    private World world;
    private Viewport viewport;
    private Stage ui;
    private InputMultiplexer multiplexer;
    private CountDown countDown;

    private Label scoreLabel;
    private Label countdownLabel;

    public GameView() {
        viewport = new ExtendViewport(WORLD_HEIGHT, WORLD_HEIGHT);
        ui = new Stage(new ScreenViewport());

        multiplexer = new InputMultiplexer(ui);

        /* UI settings */
        Skin skin = assets.get("textures/textures.json");
        Table root = new Table();
        root.setFillParent(true);
        root.setSkin(skin);
        //root.setBackground("background");

        scoreLabel = new Label("0", skin, "scoreLabel");
        countdownLabel = new Label("TOUCH", skin, "countdownLabel");
        Button pauseButton = new Button(skin, "pauseButton");


        root.row().expand();
        root.add(scoreLabel).top().left();
        root.add(pauseButton).top().right();
        root.row();
        root.add(countdownLabel).bottom().left();


        ui.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!countDown.isOver() && !countDown.isRunning()) {
                    countDown.start();
                }
            }
        });
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Go to pause view
            }
        });
        ui.addActor(root);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        ui.getViewport().update(width, height, true);
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
    }

    public void start (Difficulty difficulty) {
        game.setScreen(this);
        initialize(difficulty);
        event.start();
    }

    private void initialize(Difficulty difficulty) {
        this.difficulty = difficulty;
        world = new World(viewport.getWorldWidth(), viewport.getWorldHeight());
        event = new EndlessSalvos(world, difficulty);
        countDown = new CountDown(3);
        scoreLabel.setText(" ");
        countdownLabel.setVisible(true);
        //screen.setInputProcessor(new GameViewInput(screen, world, manager, countDown).getDetector());

    }

    @Override
    public void render(float delta) {
        /* UPDATE */
        countDown.update(delta);
        if (countDown.isOver()) {
            //manager.removeElement(countdownLabel);
            countdownLabel.setVisible(false);
            world.update(delta);
            scoreLabel.setText(String.valueOf(getScore()));
            if (event.isOver()) {
                int highScore = ScoreManager.getHighScore(difficulty);
                int score = getScore();
                boolean isHighScore = score > highScore;
                handleScore(score, isHighScore);
//                game.launchEndOfGameView(difficulty, score, isHighScore);
            }
        } else {
            if (countDown.isRunning()) {
                countdownLabel.setText(countDown.getSecondStr());
            } else {
                countdownLabel.setText(International.get(TOUCH));
            }
        }

        /* RENDER */
        viewport.apply(true);
        spriteBatch.begin();

        world.render(spriteBatch, viewport.getCamera());
        spriteBatch.end();
        ui.draw();
    }

    private void handleScore(int score, boolean isHighScore) {
        if (isHighScore) {
            ScoreManager.setHighScore(difficulty, score);
        }
    }

    public int getScore() {
        return world.getGarry().getAttackAvoided();
    }

    @Override
    public void dispose() {
        ui.dispose();
    }
}


