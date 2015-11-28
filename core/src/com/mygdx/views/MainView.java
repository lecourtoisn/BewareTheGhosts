package com.mygdx.views;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.EntityInfo;
import com.mygdx.event.DifficultySchema;
import com.mygdx.game.BTGGame;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;
import com.mygdx.userinterface.elements.Widget;
import com.mygdx.userinterface.elements.Background;

public class MainView extends ScreenListener {
    private final static int LBL_SIZE = 10;
    private static final float GAP = 1.5f * LBL_SIZE;
    private static final float MARGIN = 10;
    private static final Color TITLE_COLOR = Color.FOREST;
    private static final Color LBL_COLOR = Color.CORAL;
    private final static FileHandle TITLE_FONT = Font.KENVECTORBOLD;
    private final static FileHandle LBL_FONT = Font.KENVECTOR;

    private BTGGame game;
    private Widget background = new Background(screen.getWidth(), screen.getHeight());

    private Label normalButton = new Label(LBL_FONT, screen.getHeight(), LBL_SIZE) {
        @Override
        public void onTouched() {
            game.startGameSession(DifficultySchema.Difficulty.NORMAL);
        }
    };
    private Label hardButton = new Label(LBL_FONT, screen.getHeight(), LBL_SIZE) {
        @Override
        public void onTouched() {
            game.startGameSession(DifficultySchema.Difficulty.HARD);
        }
    };
    private Label highScoreButton = new Label(LBL_FONT, screen.getHeight(), LBL_SIZE) {
        @Override
        public void onTouched() {
            game.launchScoreView();
        }
    };
    private Label btgLbl = new Label(TITLE_FONT, screen.getHeight(), LBL_SIZE+2);
    private Widget garry = new Widget(EntityInfo.GARRY.getTexture(), new Vector2(EntityInfo.GARRY.getSize()).scl(3));

    public MainView(BTGGame game) {
        super(100);
        this.game = game;

        normalButton.setOrigin(0, 0);
        hardButton.setOrigin(0, 0);
        highScoreButton.setOrigin(0, 0);
        btgLbl.setOrigin(0, btgLbl.getGSizeY());
        garry.setOrigin(garry.getGSizeX(), 0);

        normalButton.setPosition(MARGIN, MARGIN + 2 * GAP);
        hardButton.setPosition(MARGIN, MARGIN + GAP);
        highScoreButton.setPosition(MARGIN, MARGIN);
        btgLbl.setPosition(MARGIN, screen.getHeight() - MARGIN);
        garry.setPosition(screen.getWidth()-4*MARGIN, MARGIN);

        normalButton.setColor(LBL_COLOR);
        hardButton.setColor(LBL_COLOR);
        highScoreButton.setColor(LBL_COLOR);
        btgLbl.setColor(TITLE_COLOR);


        normalButton.setText("Normal");
        hardButton.setText("Hard");
        highScoreButton.setText("High Scores");
        btgLbl.setText("Slipping Gary !");

        manager.addElement(normalButton);
        manager.addElement(hardButton);
        manager.addElement(highScoreButton);
        manager.addElement(btgLbl);
        manager.addElement(garry);

        screen.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Widget touched = manager.getElementAt(screen.unProject(screenX, screenY));
                if (touched != null) {
                    touched.onTouched();
                }
                return false;
            }
        });
    }

    public void start() {
        game.setScreen(screen);
    }

    @Override
    public void render(Batch batch, Camera cam) {
        background.draw(batch);
        manager.draw(batch);
    }
}
