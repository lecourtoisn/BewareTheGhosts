package com.mygdx.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.Set;

public class WidgetManager {
    private Set<Widget> elements;

    public WidgetManager() {
        elements = new HashSet<Widget>();
    }

    public void addElement(Widget element) {
        elements.add(element);
    }

    public Widget getElementAt(Vector2 position) {
        for (Widget element : elements) {
            if (element.getGraphicBounds().contains(position)) {
                return element;
            }
        }
        return null;
    }

    public void draw(Batch batch) {
        for (Widget element : elements) {
            element.draw(batch);
        }
    }

    public void removeElement(Widget element) {
        elements.remove(element);
    }
}
