package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.AbstractCoreEntity;

import java.util.List;

public interface JpaCommonEntityService<T extends AbstractCoreEntity> {

    void update(T entity);

    void add(T entity);

    void delete(T entity);

    T getById(Long id);

    List<T> getAllEntity();
}
