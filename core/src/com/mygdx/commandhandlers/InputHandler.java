package com.mygdx.commandhandlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.entity.Garry;
import com.mygdx.util.Direction;
import com.mygdx.world.World;

public class InputHandler implements InputProcessor {

    private World world;

    public InputHandler(World world) {
        this.world = world;
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
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
