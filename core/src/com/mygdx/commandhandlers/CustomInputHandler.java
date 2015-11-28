package com.mygdx.commandhandlers;

import com.badlogic.gdx.input.GestureDetector;
import com.mygdx.screen.UnStretchedScreen;
import com.mygdx.userinterface.WidgetManager;
import com.mygdx.userinterface.elements.Widget;

public class CustomInputHandler extends GestureDetector.GestureAdapter {

    private WidgetManager manager;
    private UnStretchedScreen screen;

    public CustomInputHandler() {
        super();
    }

    public CustomInputHandler(UnStretchedScreen screen, WidgetManager manager) {
        this.manager = manager;
        this.screen = screen;
    }

    public void keyDown(int keycode) {
        // Does nothing by default
    }

    public void keyUp(int keycode) {
        // Does nothing by default
    }

    public void onTap(float x, float y, int count, int button) {
        // Does nothing by default
    }


    /** Default Tap Methods **/
    @Override
    public final boolean tap(float x, float y, int count, int button) {
        // Handle Widget by default
        if (manager != null) {
            Widget touched = manager.getElementAt(screen.unProject(x, y));
            if (touched != null) {
                touched.onTouched();
            }
        }
        onTap(x, y, count, button);
        return false;
    }


    /** Input Redirection **/
    public GestureDetector getDetector() {
        return detector;
    }

    private GestureDetector detector = new GestureDetector(this) {
        @Override
        public boolean keyDown(int keycode) {
            CustomInputHandler.this.keyDown(keycode);
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            CustomInputHandler.this.keyUp(keycode);
            return false;
        }

    };
}
