package com.mygdx.views;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.BTGGame;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;
import com.mygdx.userinterface.elements.ResumeLabel;
import com.mygdx.userinterface.elements.Widget;
import com.mygdx.util.International;

import static com.mygdx.util.International.Label.PAUSE;
import static com.mygdx.util.International.Label.RESUME;

public class PauseView extends ScreenListener {
    private final static float HEIGHT = 100;

    private Label pauseLbl;
    private ResumeLabel resumeLbl;
    private BTGGame game;

    public PauseView(BTGGame game, final GameView gameView) {
        super(HEIGHT);
        this.game = game;
        pauseLbl = new Label(Font.CALIBRI, screen.getHeight(), 30);
        resumeLbl = new ResumeLabel(gameView, screen.getHeight());
        pauseLbl.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 + 25);
        resumeLbl.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 - 10);
        pauseLbl.setText(International.get(PAUSE));
        resumeLbl.setText(International.get(RESUME));

        manager.addElement(pauseLbl);
        manager.addElement(resumeLbl);

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

    @Override
    public void render(Batch batch, Camera cam) {
        manager.draw(batch);
    }

    public void start() {
        game.setScreen(screen);
    }
}
