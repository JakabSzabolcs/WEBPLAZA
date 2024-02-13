package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.CoreEntity;

import java.util.List;

public interface JpaCommonEntityService<T extends CoreEntity> {

    public void update(T entity);

    public void delete(T entity);

    public T getById(T entityClass, Long id);

    public List<T> getAllEntity(T entityClass);
}
