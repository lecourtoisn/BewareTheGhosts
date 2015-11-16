package com.mygdx.util;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
    LEFT, UP, RIGHT, DOWN, NONE;

    public enum Axe{
        X, Y
    }

    public Axe getAxe() {
        Axe axe;
        switch (this) {
            case LEFT:
            case RIGHT:
                axe = Axe.X;
                break;
            case UP:
            case DOWN:
                axe = Axe.Y;
                break;
            default:
                axe = null;
        }
        return axe;
    }

    public Direction getOpposite() {
        Direction opp;
        switch (this) {
            case LEFT:
                opp = RIGHT;
                break;
            case RIGHT:
                opp = LEFT;
                break;
            case UP:
                opp = DOWN;
                break;
            case DOWN:
                opp = UP;
                break;
            default:
                opp = null;
        }
        return opp;
    }

    public Vector2 getUnitVector() {
        Vector2 movement = new Vector2();
        switch (this) {
            case LEFT:
                movement.set(-1, 0);
                break;
            case RIGHT:
                movement.set(1, 0);
                break;
            case UP:
                movement.set(0, 1);
                break;
            case DOWN:
                movement.set(0, -1);
                break;
            default:
                movement.set(0, 0);
        }
        return movement;
    }

    public static Direction[] getDirections() {
        return new Direction[]{LEFT, UP, RIGHT, DOWN};
    }
}
