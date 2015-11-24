package com.mygdx.util;

public class RelativeStopWatch {
    private long elapsedTime;
    private boolean hasStarted = false;

    public void update(float deltaInSecond) {
        elapsedTime += deltaInSecond*1000;
    }

    public void start() {
        start(0);
    }

    public void start(long delay) {
        elapsedTime = delay;
        hasStarted = true;
    }

    private long getElapsedTime() {
        if (hasStarted) {
            return elapsedTime;
        }
        return 0;
    }

    public long getMilliseconds() {
        return getElapsedTime();
    }

    public long getSeconds() {
        return getElapsedTime()/1000;
    }

    public void restart() {
        start();
    }

    public void stop() {
        this.hasStarted = false;
    }

    public boolean isRunning() {
        return hasStarted;
    }
}
