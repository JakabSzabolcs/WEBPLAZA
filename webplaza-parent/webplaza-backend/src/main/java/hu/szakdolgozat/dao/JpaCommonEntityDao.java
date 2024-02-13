package hu.szakdolgozat.dao;

import hu.szakdolgozat.entity.CoreEntity;

import java.util.List;

public interface JpaCommonEntityDao<T extends CoreEntity> {
    List<T> getAllEntity(T entity);

    void add(T entity);

    void remove(Long id);

    T getById(Long id);

    void update(T entity);
}
