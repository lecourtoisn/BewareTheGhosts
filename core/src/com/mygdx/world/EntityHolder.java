package com.mygdx.world;

import com.mygdx.entity.IEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityHolder {
    private Map<Class<? extends IEntity>, Set<IEntity>> entitiesMap;
    private Set<IEntity> entitiesSet;

    public EntityHolder() {
        this.entitiesMap = new HashMap<Class<? extends IEntity>, Set<IEntity>>();
        this.entitiesSet = new HashSet<IEntity>();
    }

    public void add(IEntity entity) {
        getEntities(entity.getClass()).add(entity);
        entitiesSet.add(entity);
    }

    public void addAll(Set<? extends IEntity> entities) {
        for (IEntity entity : entities) {
            add(entity);
        }
    }

    public void remove(IEntity entity) {
        getEntities(entity.getClass()).remove(entity);
        entitiesSet.remove(entity);
    }

    public void removeAll(Set<? extends IEntity> entities) {
        for (IEntity entity : entities) {
            remove(entity);
        }
    }

    public Set<IEntity> getEntities() {
        return new HashSet<IEntity>(entitiesSet);
    }

    public Set<IEntity> getEntities(Class<? extends IEntity> entityType) {
        if (!entitiesMap.containsKey(entityType)) {
            entitiesMap.put(entityType, new HashSet<IEntity>());
        }
        return entitiesMap.get(entityType);
    }
}
