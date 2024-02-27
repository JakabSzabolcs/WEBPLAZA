package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.dao.JpaCommonEntityDao;
import hu.szakdolgozat.entity.AbstractCoreEntity;
import hu.szakdolgozat.service.JpaCommonEntityService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

public abstract class JpaCommonEntityServiceImpl<T extends AbstractCoreEntity> implements JpaCommonEntityService<T> {

    @Inject
    private JpaCommonEntityDao<T> dao;

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void add(T entity) {
        dao.add(entity);
    }

    @Override
    public void delete(T entity) {
        dao.remove(entity.getId());
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional
    public List<T> getAllEntity() {
        return dao.getAllEntity();
    }
}
