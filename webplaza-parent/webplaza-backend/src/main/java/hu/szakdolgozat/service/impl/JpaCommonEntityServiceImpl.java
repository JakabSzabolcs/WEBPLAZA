package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.dao.JpaCommonEntityDao;
import hu.szakdolgozat.entity.CoreEntity;
import hu.szakdolgozat.service.JpaCommonEntityService;

import javax.inject.Inject;
import java.util.List;

public abstract class JpaCommonEntityServiceImpl<T extends CoreEntity> implements JpaCommonEntityService<T> {

    @Inject
    private JpaCommonEntityDao<T> dao;

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void delete(T entity) {
        dao.remove(entity.getId());
    }

    @Override
    public T getById(T entityClass, Long id) {
        return dao.getById(id);
    }

    @Override
    public List<T> getAllEntity(T entityClass) {
        return dao.getAllEntity(entityClass);
    }
}
