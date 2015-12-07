package com.mygdx.commandhandlers;

import com.badlogic.gdx.Input;
import com.mygdx.entity.Garry;
import com.mygdx.util.Direction;
import com.mygdx.world.World;

public class GameViewInput extends CustomInputHandler {

    private static final float LIMIT = 35;
    private World world;
    private float fdy = 0;
    private float fdx = 0;
    private Direction lastMove;

    public void setWorld(World world) {
        this.world = world;
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
}
