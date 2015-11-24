package com.mygdx.userinterface;

import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.Set;

public class UIManager {
    private Set<IUIElement> elements;

    public UIManager() {
        elements = new HashSet<IUIElement>();
    }

    public void addElement(IUIElement element) {
        elements.add(element);
    }

    public IUIElement getElementAt(Vector2 position) {
        for (IUIElement element : elements) {
            if (element.getHitbox().contains(position)) {
                return element;
            }
        }
        return null;
    }
}
