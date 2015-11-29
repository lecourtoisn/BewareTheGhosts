package com.mygdx.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.commandhandlers.CustomInputHandler;
import com.mygdx.game.BTGGame;
import com.mygdx.screen.ScreenListener;
import com.mygdx.userinterface.elements.Font;
import com.mygdx.userinterface.elements.Label;
import com.mygdx.userinterface.elements.ResumeLabel;
import com.mygdx.util.International;

import static com.mygdx.util.International.Label.PAUSE;
import static com.mygdx.util.International.Label.RESUME;

public class PauseView extends ScreenListener {
    private final static float HEIGHT = 100;

    private Label pauseLbl;
    private ResumeLabel resumeLbl;
    private BTGGame game;

    public PauseView(BTGGame game) {
        super(HEIGHT);
        this.game = game;
        pauseLbl = new Label(Font.CALIBRI, screen.getHeight(), 30);
        resumeLbl = new ResumeLabel(game, screen.getHeight());
        pauseLbl.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 + 25);
        resumeLbl.setPosition(screen.getWidth() / 2, screen.getHeight() / 2 - 10);
        pauseLbl.setText(International.get(PAUSE));
        resumeLbl.setText(International.get(RESUME));

        manager.addElement(pauseLbl);
        manager.addElement(resumeLbl);

        screen.setInputProcessor(new CustomInputHandler(screen, manager).getDetector());
    }

    @Override
    public void render(Batch batch, Camera cam) {
        manager.draw(batch);
    }

    public void start() {
        game.setScreen(screen);
    }
}
