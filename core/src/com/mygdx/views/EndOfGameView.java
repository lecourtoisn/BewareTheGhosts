package com.mygdx.views;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.event.DifficultySchema.Difficulty;
import com.mygdx.game.BTGGame;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;
import com.mygdx.userinterface.elements.Widget;
import com.mygdx.util.International;

import static com.mygdx.util.International.Label.*;

public class EndOfGameView extends ScreenListener {
    private static final float MARGIN = 15;
    private static final float GAP = 10;
    private Difficulty difficulty;
    private BTGGame game;

    private Label yourScoreLbl = new Label(Font.CALIBRI, screen.getHeight(), 25);
    private Label againBtn = new Label(Font.CALIBRI, screen.getHeight(), 15) {
        @Override
        public void onTouched() {
            game.startGameSession(difficulty);
        }
    };
    private Label menuBtn = new Label(Font.CALIBRI, screen.getHeight(), 15) {
        @Override
        public void onTouched() {
            game.launchMainView();
        }
    };

    public EndOfGameView(BTGGame game) {
        super(100);
        this.game = game;

        float middleWidth = screen.getWidth()/2;
        float middleHeight = screen.getHeight()/2;

        yourScoreLbl.setOriginY(yourScoreLbl.getGSizeY());
        againBtn.setOriginY(0);
        menuBtn.setOriginY(0);

        yourScoreLbl.setPosition(middleWidth, screen.getHeight() - MARGIN);
        againBtn.setPosition(middleWidth, MARGIN*2+GAP);
        menuBtn.setPosition(middleWidth, MARGIN);

        againBtn.setText(International.get(PLAYAGAIN));
        menuBtn.setText(International.get(MENU));

        manager.addElement(yourScoreLbl);
        manager.addElement(againBtn);
        manager.addElement(menuBtn);

        screen.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Widget widget = manager.getElementAt(screen.unProject(screenX, screenY));
                if (widget != null) {
                    widget.onTouched();
                }
                return false;
            }
        });
    }

    @Override
    public void render(Batch batch, Camera cam) {
        manager.draw(batch);
    }

    public void start(Difficulty difficulty, Integer score, Boolean isHighScore) {
        game.setScreen(screen);
        this.difficulty = difficulty;
        String text = isHighScore ? International.get(NEWHIGHSCORE) : International.get(SCORE);
        text += " : " + score;
        text += isHighScore ? "!" : "";
        yourScoreLbl.setText(text);

    }
}
