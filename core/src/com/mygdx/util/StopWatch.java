package com.mygdx.util;

public class StopWatch {
    private long begin;

    public void start(){
        begin = System.currentTimeMillis();
    }

    public void restart() {
        start();
    }

    public long getTime() {
        return System.currentTimeMillis()-begin;
    }

    public long getMilliseconds() {
        return System.currentTimeMillis()-begin;
    }

    public double getSeconds() {
        return (System.currentTimeMillis() - begin) / 1000.0;
    }
}