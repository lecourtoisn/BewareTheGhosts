package com.mygdx.commandhandlers;

import com.badlogic.gdx.Input;
import com.mygdx.entity.Garry;
import com.mygdx.screen.UnStretchedScreen;
import com.mygdx.userinterface.WidgetManager;
import com.mygdx.util.CountDown;
import com.mygdx.util.Direction;
import com.mygdx.world.World;

public class GameViewInput extends CustomInputHandler {
    private final UnStretchedScreen screen;
    private final World world;
    private final WidgetManager widgetManager;
    private final CountDown countDown;

    public GameViewInput(UnStretchedScreen screen, World world, WidgetManager widgetManager, CountDown countDown) {
        super(screen, widgetManager);
        this.screen = screen;
        this.world = world;
        this.widgetManager = widgetManager;
        this.countDown = countDown;
    }

    @Override
    public void keyDown(int keycode) {
        Garry garry = world.getGarry();
        switch (keycode) {
            case Input.Keys.UP:
                garry.setMovingDirection(Direction.UP);
                break;
            case Input.Keys.RIGHT:
                garry.setMovingDirection(Direction.RIGHT);
                break;
            case Input.Keys.DOWN:
                garry.setMovingDirection(Direction.DOWN);
                break;
            case Input.Keys.LEFT:
                garry.setMovingDirection(Direction.LEFT);
                break;
        }
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        if (!countDown.isOver() && !countDown.isRunning()) {
            countDown.start();
        }
    }
}
