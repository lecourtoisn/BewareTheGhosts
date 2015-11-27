package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.Font;
import com.mygdx.userinterface.Label;
import com.mygdx.userinterface.ResumeLabel;
import com.mygdx.userinterface.Widget;

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
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//                Vector3 tp = screen.getCamera().unproject(new Vector3(screenX, screenY, 0));
//                Vector2 worldPosition = new Vector2(tp.x, tp.y);
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
