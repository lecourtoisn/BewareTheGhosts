package com.mygdx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {

    //private static Random random = new Random(4815162342l);
    private static Random random = new Random();

    public static int getInt(int exclusiveBound) {
        return random.nextInt(exclusiveBound);
    }

    /**
     * Excluding "NONE"
     * @return a random direction
     */
    public static Direction getDirection() {
        Direction[] values = Direction.getDirections();
        int pick = getInt(values.length);
        return values[pick];
    }

    /*public static Direction getDirection(List<Direction> excludedDir) {
        if (excludedDir.size() == Direction.getDirections().length) {
            return null;
        }

        Direction dir =
       Direction direction;
        do {
            direction = getDirection();
        } while (excludedDir.contains(direction));
        return direction;
    }*/

    public static List<Integer> getXInt(int x, int exclusiveBound) {
        List<Integer> ints = new ArrayList<Integer>();

        for (int i = 0; i < x; i++) {
            int newInt;
            do {
                newInt = getInt(exclusiveBound);
            } while (ints.contains(newInt));
            ints.add(newInt);
        }
        return ints;
    }

    public static float getFloat() {
        return random.nextFloat();
    }

    public static <T> List<T> getXInList(int x, List<T> list) {
        List<T> test = new ArrayList<T>();
        for (int i = 0; i < x; i++) {
            int random = getInt(list.size());
            test.add(list.remove(random));
        }
        return test;
    }
}
