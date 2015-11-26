package com.mygdx.commandhandlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.entity.Garry;
import com.mygdx.userinterface.Widget;
import com.mygdx.screen.UnStretchedScreen;
import com.mygdx.userinterface.WidgetManager;
import com.mygdx.util.Direction;
import com.mygdx.world.World;

public class GameSessionInputHandler extends InputAdapter{

    private UnStretchedScreen screen;
    private WidgetManager widgetManager;
    private World world;

    public GameSessionInputHandler(UnStretchedScreen screen, World world, WidgetManager widgetManager) {
        this.world = world;
        this.widgetManager = widgetManager;
        this.screen = screen;
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
                garry.setDead(false);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 tp = screen.getCamera().unproject(new Vector3(screenX, screenY, 0));
        Vector2 worldPosition = new Vector2(tp.x, tp.y);
        Widget touched = widgetManager.getElementAt(worldPosition);
        if (touched != null) {
            touched.onTouched();
        }
        return true;
    }
}
