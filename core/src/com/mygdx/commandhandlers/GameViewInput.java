package com.mygdx.commandhandlers;

import com.badlogic.gdx.Input;
import com.mygdx.entity.Garry;
import com.mygdx.screen.UnStretchedScreen;
import com.mygdx.userinterface.WidgetManager;
import com.mygdx.util.CountDown;
import com.mygdx.util.Direction;
import com.mygdx.world.World;

public class GameViewInput extends CustomInputHandler {
    private static final float LIMIT = 35;
    private final UnStretchedScreen screen;
    private final World world;
    private final WidgetManager widgetManager;
    private final CountDown countDown;
    private Direction lastMove;

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

    private float fdx = 0;
    private float fdy = 0;

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Direction dir = null;
        deltaX += fdx;
        deltaY += fdy;
        if (Math.abs(deltaX) > LIMIT) {
            dir = (deltaX < 0 ? Direction.LEFT : Direction.RIGHT);
        } else if (Math.abs(deltaY) > LIMIT) {
            dir = (deltaY < 0 ? Direction.UP : Direction.DOWN);
        }
        if (dir != null && (lastMove == null || dir != lastMove)) {
            Garry garry = world.getGarry();
            garry.setMovingDirection(dir);
            lastMove = dir;
            fdx = deltaX;
            fdy = deltaY;
        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        lastMove = null;
        fdx = 0;
        fdy = 0;
        return super.panStop(x, y, pointer, button);
    }

    /*@Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Garry garry = world.getGarry();
        if(Math.abs(velocityX)>Math.abs(velocityY)){
            if(velocityX>0){
                garry.setMovingDirection(Direction.RIGHT);
            }else{
                garry.setMovingDirection(Direction.LEFT);
            }
        }else{
            if(velocityY>0){
                garry.setMovingDirection(Direction.DOWN);
            }else{
                garry.setMovingDirection(Direction.UP);
            }
        }
        return super.fling(velocityX, velocityY, button);
    }*/
}
