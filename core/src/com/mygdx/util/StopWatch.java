/*
package com.mygdx.util;

public class StopWatch {
    private long begin;
    private boolean stopped = true;

    private long getDifference() {
        long difference;
        if (stopped) {
            difference = 0;
        } else {
            difference = System.currentTimeMillis()-begin;
        }
        return difference;
    }

    public void start(){
        start(0);
    }

    public void start(long delay) {
        stopped = false;
        begin = System.currentTimeMillis() - delay;
    }

    public void restart() {
        start();
    }

    public long getMilliseconds() {
        return getDifference();
    }

    public double getSeconds() {
        return (getDifference()) / 1000.0;
    }
}*/
