package com.mygdx.util;

public class CountDown {
    private boolean isRunning;
    private boolean isOver;
    private RelativeStopWatch stopWatch;
    private long countDownTime;

    public CountDown(int seconds) {
        countDownTime = seconds*1000;
        isRunning = false;
        isOver = false;
        stopWatch = new RelativeStopWatch();
    }

    public void start() {
        isRunning = true;
        stopWatch.start();
    }

    public void reset() {
        isRunning = false;
        isOver = false;
        stopWatch.reset();
    }

    public void update(float delta) {
        stopWatch.update(delta);
        if (stopWatch.getMilliseconds() > countDownTime) {
            isOver = true;
            isRunning = false;
            stopWatch.stop();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isOver() {
        return isOver;
    }

    public String getSecondStr() {
        return String.valueOf(1 + getElapsedTime()/1000);
    }

    private long getElapsedTime() {
        return isOver() ? 0 : countDownTime - stopWatch.getMilliseconds();
    }
}
