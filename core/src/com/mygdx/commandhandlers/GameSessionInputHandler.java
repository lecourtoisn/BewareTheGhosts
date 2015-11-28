package com.mygdx.commandhandlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.entity.Garry;
import com.mygdx.util.CountDown;
import com.mygdx.screen.UnStretchedScreen;
import com.mygdx.userinterface.elements.Widget;
import com.mygdx.userinterface.WidgetManager;
import com.mygdx.util.Direction;
import com.mygdx.world.World;

public class GameSessionInputHandler extends InputAdapter{

    private UnStretchedScreen screen;
    private WidgetManager widgetManager;
    private World world;
    private CountDown countDown;

    public GameSessionInputHandler(UnStretchedScreen screen, World world, WidgetManager widgetManager, CountDown countDown) {
        this.world = world;
        this.widgetManager = widgetManager;
        this.screen = screen;
        this.countDown = countDown;
    }

    @Override
    public boolean keyDown(int keycode) {
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
            case Input.Keys.SPACE:
                if (!countDown.isOver() && !countDown.isRunning()) {
                    countDown.start();
                }
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Widget touched = widgetManager.getElementAt(screen.unProject(screenX, screenY));
        if (touched != null) {
            touched.onTouched();
        }
        return true;
    }


}
