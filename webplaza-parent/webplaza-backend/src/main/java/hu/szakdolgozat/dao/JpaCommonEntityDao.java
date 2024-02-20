package hu.szakdolgozat.dao;

import hu.szakdolgozat.entity.AbstractCoreEntity;

import java.util.List;

public interface JpaCommonEntityDao<T extends AbstractCoreEntity> {
    List<T> getAllEntity();

    void add(T entity);

    void remove(Long id);

    T getById(Long id);

    void update(T entity);
}
