package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.CoreEntity;

import java.util.List;

public interface JpaCommonEntityService <T extends CoreEntity> {

    public <T> T save(T entity);

    public <T> T update(T entity);

    public <T> void delete(T entity);

    public <T> T getById(Class<T> entityClass, Long id);

    public <T> List<T> getAll(Class<T> entityClass);

}
