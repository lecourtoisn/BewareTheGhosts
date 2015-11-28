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
    private final static FileHandle LBL_FONT = Font.KENVECTOR;
    private final static FileHandle TITLE_FONT = Font.KENVECTORBOLD;
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
            game.launchSimpleMenu();
        }
    };
    private Label btgLbl = new Label(TITLE_FONT, screen.getHeight(), LBL_SIZE+2);
    private Widget garry = new Widget(EntityInfo.GARRY.getTexture(), new Vector2(EntityInfo.GARRY.getSize()).scl(3));

    public MainView(BTGGame game) {
        super(100);
        this.game = game;

        float gap = LBL_SIZE*1.5f;
        Color color = Color.CORAL;

        float widthPosition = 10;
        float heightPosition = widthPosition;
        normalButton.setOrigin(0, 0);
        hardButton.setOrigin(0, 0);
        highScoreButton.setOrigin(0, 0);
        btgLbl.setOrigin(0, btgLbl.getGraphicSize().y);
        garry.setOrigin(garry.getGraphicSize().x, 0);

        normalButton.setPosition(widthPosition, heightPosition + 2 * gap);
        hardButton.setPosition(widthPosition, heightPosition + gap);
        highScoreButton.setPosition(widthPosition, heightPosition);
        btgLbl.setPosition(widthPosition, screen.getHeight() - heightPosition);
        garry.setPosition(screen.getWidth()-4*widthPosition, heightPosition);

        normalButton.setColor(color);
        hardButton.setColor(color);
        highScoreButton.setColor(color);
        btgLbl.setColor(Color.FOREST);


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
