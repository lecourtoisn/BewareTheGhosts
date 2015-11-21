package com.mygdx.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenericHolder<T> {
    private Map<Class<? extends T>, Set<T>> entitiesMap;
    private Set<T> entitiesSet;

    public GenericHolder() {
        this.entitiesMap = new HashMap<Class<? extends T>, Set<T>>();
        this.entitiesSet = new HashSet<T>();
    }

    @SuppressWarnings("unchecked")
    public void add(T entity) {
        getEntities((Class<? extends T>) entity.getClass()).add(entity);
        entitiesSet.add(entity);
    }

    public void addAll(Set<? extends T> entities) {
        for (T entity : entities) {
            add(entity);
        }
    }

    @SuppressWarnings("unchecked")
    public void remove(T entity) {
        getEntities((Class<? extends T>) entity.getClass()).remove(entity);
        entitiesSet.remove(entity);
    }

    public void removeAll(Set<? extends T> entities) {
        for (T entity : entities) {
            remove(entity);
        }
    }

    public Set<T> getEntities() {
        return new HashSet<T>(entitiesSet);
    }

    public Set<T> getEntities(Class<? extends T> entityType) {
        if (!entitiesMap.containsKey(entityType)) {
            entitiesMap.put(entityType, new HashSet<T>());
        }
        return entitiesMap.get(entityType);
    }
}
