package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.CoreEntity;

import java.util.List;

public interface JpaCommonEntityService<T extends CoreEntity> {

    void update(T entity);

    void delete(T entity);

    T getById(Long id);

    List<T> getAllEntity();
}
