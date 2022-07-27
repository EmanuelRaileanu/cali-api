package com.strength.caliapi;

import com.strength.caliapi.entities.db.BaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockRepository<T extends BaseModel> {
    private final List<T> entities = new ArrayList<>();

    List<T> findAll() {
        return this.entities;
    }

    Optional<T> findById(long id) {
        return this.entities.stream().filter(entity -> entity.getId() == id).findFirst();
    }

    T create(T entity) {
        this.entities.add(entity);
        return entity;
    }

    T update(T entity) {
        int entityIndex = this.entities.indexOf(entity);
        if (entityIndex != -1) {
            this.entities.set(entityIndex, entity);
        }
        return entity;
    }

    void deleteById(long id) {
        this.entities.removeIf(entity -> entity.getId() == id);
    }
}
