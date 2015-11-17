package com.mygdx.event;

public interface IEvent {
    void start();
    void process(float delta);
    void end();
    boolean isHappening();

}
