package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.userinterface.*;
import com.mygdx.screen.ScreenListener;

public class PauseMenu extends ScreenListener {
    private final static float HEIGHT = 100;

    private Label pauseLbl;
    private ResumeLabel resumeLbl;
    private BTGGame game;

    public PauseMenu(BTGGame game, final GameSession gameSession) {
        super(HEIGHT);
        this.game = game;
        pauseLbl = new Label(Font.CALIBRI, screen.getHeight(), 30);
        resumeLbl = new ResumeLabel(gameSession, screen.getHeight());
        pauseLbl.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 + 25);
        resumeLbl.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 - 10);
        pauseLbl.setText("Pause");
        resumeLbl.setText("Resume");

        manager.addElement(pauseLbl);
        manager.addElement(resumeLbl);

        screen.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Vector3 tp = screen.getCamera().unproject(new Vector3(screenX, screenY, 0));
                Vector2 worldPosition = new Vector2(tp.x, tp.y);
                Widget touched = manager.getElementAt(worldPosition);
                if (touched != null) {
                    touched.onTouched();
                }
                return true;
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
