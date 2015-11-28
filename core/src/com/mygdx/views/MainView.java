package com.mygdx.views;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.commandhandlers.CustomInputHandler;
import com.mygdx.entity.EntityInfo;
import com.mygdx.event.DifficultySchema;
import com.mygdx.game.BTGGame;
import com.mygdx.game.TokenManager;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Background;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;
import com.mygdx.userinterface.elements.Widget;
import com.mygdx.util.CountDown;
import com.mygdx.util.International;

import static com.mygdx.util.International.Label.*;

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
    private Label tokenQuantity = new Label(Font.KENVECTOR, screen.getHeight(), 7);
    private Label tokenCountDown = new Label(Font.KENVECTOR, screen.getHeight(), 3);
    //private Widget tokenImg = new Widget(new Texture("token.png"), new Vector2(5, 4.2452283019f));
    private Widget garry = new Widget(EntityInfo.GARRY.getTexture(), new Vector2(EntityInfo.GARRY.getSize()).scl(3));
    private CountDown countDown = new CountDown(1);

    public MainView(BTGGame game) {
        super(100);
        this.game = game;
        countDown.start();

        normalButton.setOrigin(0, 0);
        hardButton.setOrigin(0, 0);
        highScoreButton.setOrigin(0, 0);
        btgLbl.setOrigin(0, btgLbl.getGSizeY());
        garry.setOrigin(garry.getGSizeX(), 0);
        //tokenImg.setOriginX(tokenImg.getGSizeX());
        tokenQuantity.setOrigin(tokenQuantity.getGSizeX(), 0);
        tokenCountDown.setOrigin(tokenCountDown.getGSizeX(), tokenCountDown.getGSizeY());


        normalButton.setColor(LBL_COLOR);
        hardButton.setColor(LBL_COLOR);
        highScoreButton.setColor(LBL_COLOR);
        btgLbl.setColor(TITLE_COLOR);
        tokenCountDown.setColor(LBL_COLOR);
        tokenQuantity.setColor(LBL_COLOR);

        normalButton.setText(International.get(NORMALLBL));
        hardButton.setText(International.get(HARDLBL));
        highScoreButton.setText(International.get(HIGHSCORE));
        btgLbl.setText(International.get(TITLE));

        normalButton.setPosition(MARGIN, MARGIN + 2 * GAP);
        hardButton.setPosition(MARGIN, MARGIN + GAP);
        highScoreButton.setPosition(MARGIN, MARGIN);
        btgLbl.setPosition(MARGIN, screen.getHeight() - MARGIN);
        garry.setPosition(screen.getWidth() - 4 * MARGIN, MARGIN);
        //tokenImg.setPosition(screen.getWidth()-MARGIN, screen.getHeight() - MARGIN);
        tokenQuantity.setPosition(screen.getWidth()-MARGIN, btgLbl.getGPosY());
        tokenCountDown.setPosition(tokenQuantity.getPosX(), tokenQuantity.getPosY() - 2);

        manager.addElement(normalButton);
        manager.addElement(hardButton);
        manager.addElement(highScoreButton);
        manager.addElement(btgLbl);
        manager.addElement(garry);
        manager.addElement(tokenCountDown);
        //manager.addElement(tokenImg);
        manager.addElement(tokenQuantity);

        screen.setInputProcessor(new CustomInputHandler(screen, manager).getDetector());
    }

    public void start() {
        game.setScreen(screen);
    }

    @Override
    public void update(float delta) {
        tokenCountDown.setText(TokenManager.getRemainingSecondsStr());
        tokenQuantity.setText(TokenManager.getNbToken() + "/" + TokenManager.NB_TOKEN_MAX);
    }

    @Override
    public void render(Batch batch, Camera cam) {
        background.draw(batch);
        manager.draw(batch);
    }
}
