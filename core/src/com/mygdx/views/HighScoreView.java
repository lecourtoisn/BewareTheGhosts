package com.mygdx.views;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.commandhandlers.CustomInputHandler;
import com.mygdx.game.BTGGame;
import com.mygdx.game.Score;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Background;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;
import com.mygdx.userinterface.elements.Widget;
import com.mygdx.util.International;

import static com.mygdx.event.DifficultySchema.Difficulty.HARD;
import static com.mygdx.event.DifficultySchema.Difficulty.NORMAL;
import static com.mygdx.util.International.Label.*;

public class HighScoreView extends ScreenListener {
    private final static int LBL_SIZE = 10;
    private static final float GAP = 1.5f * LBL_SIZE;
    private static final float MARGIN = 10;
    private static final Color TITLE_COLOR = Color.FOREST;
    private static final Color LBL_COLOR = Color.CORAL;
    private final static FileHandle TITLE_FONT = Font.KENVECTORBOLD;
    private final static FileHandle LBL_FONT = Font.KENVECTOR;

    private BTGGame game;

    private Widget background = new Background(screen.getWidth(), screen.getHeight());

    private Label titleLbl= new Label(TITLE_FONT, screen.getHeight(), LBL_SIZE);
    private Label normalLbl = new Label(LBL_FONT, screen.getHeight(), LBL_SIZE);
    private Label hardLbl= new Label(LBL_FONT, screen.getHeight(), LBL_SIZE);

    private Label normalScore = new Label(LBL_FONT, screen.getHeight(), LBL_SIZE);
    private Label hardScore = new Label(LBL_FONT, screen.getHeight(), LBL_SIZE);

    public HighScoreView(final BTGGame game) {
        super(100);
        this.game = game;

        normalLbl.setOrigin(0, 0);
        hardLbl.setOrigin(0, 0);
        titleLbl.setOrigin(0, titleLbl.getGSizeY());
        hardScore.setOrigin(0, 0);
        normalScore.setOrigin(0, 0);

        normalLbl.setColor(LBL_COLOR);
        hardLbl.setColor(LBL_COLOR);
        titleLbl.setColor(TITLE_COLOR);
        normalScore.setColor(TITLE_COLOR);
        hardScore.setColor(TITLE_COLOR);

        normalLbl.setText(International.get(NORMALLBL));
        hardLbl.setText(International.get(HARDLBL));
        titleLbl.setText(International.get(HIGHSCORE));

        normalLbl.setPosition(MARGIN, MARGIN + GAP);
        hardLbl.setPosition(MARGIN, MARGIN);
        titleLbl.setPosition(MARGIN, screen.getHeight() - MARGIN);
        float scoresMargin = Math.max(normalLbl.getGSizeX(), hardScore.getGSizeX()) + MARGIN;
        normalScore.setPosition(scoresMargin + 20, normalLbl.getPosition().getY());
        hardScore.setPosition(scoresMargin + 20, hardLbl.getPosition().getY());

        manager.addElement(normalLbl);
        manager.addElement(hardLbl);
        manager.addElement(titleLbl);
        manager.addElement(normalScore);
        manager.addElement(hardScore);

        screen.setInputProcessor(new CustomInputHandler() {
            @Override
            public void onTap(float x, float y, int count, int button) {
                game.launchMainView();
            }
        }.getDetector());

    }

    public void start() {
        refresh();
        game.setScreen(screen);
    }

    private void refresh() {
        normalScore.setText(String.valueOf(Score.getHighScore(NORMAL)));
        hardScore.setText(String.valueOf(Score.getHighScore(HARD)));
    }

    @Override
    public void render(Batch batch, Camera cam) {
        background.draw(batch);
        manager.draw(batch);
    }
}
