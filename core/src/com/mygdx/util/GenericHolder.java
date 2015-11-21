package com.mygdx.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenericHolder<T> {
    private Map<Class<? extends T>, Set<T>> elementMap;
    private Set<T> elementSet;

    public GenericHolder() {
        this.elementMap = new HashMap<Class<? extends T>, Set<T>>();
        this.elementSet = new HashSet<T>();
    }

    @SuppressWarnings("unchecked")
    public void add(T element) {
        getElements((Class<? extends T>) element.getClass()).add(element);
        elementSet.add(element);
    }

    public void addAll(Set<? extends T> entities) {
        for (T entity : entities) {
            add(entity);
        }
    }

    @SuppressWarnings("unchecked")
    public void remove(T element) {
        getElements((Class<? extends T>) element.getClass()).remove(element);
        elementSet.remove(element);
    }

    public void removeAll(Set<? extends T> entities) {
        for (T element : entities) {
            remove(element);
        }
    }

    public Set<T> getElements() {
        return new HashSet<T>(elementSet);
    }

    public Set<T> getElements(Class<? extends T> elementType) {
        if (!elementMap.containsKey(elementType)) {
            elementMap.put(elementType, new HashSet<T>());
        }
        return elementMap.get(elementType);
    }
}
