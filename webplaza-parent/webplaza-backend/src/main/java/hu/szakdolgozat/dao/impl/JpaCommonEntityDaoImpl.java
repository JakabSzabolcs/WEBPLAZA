package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.JpaCommonEntityDao;
import hu.szakdolgozat.entity.AbstractCoreEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class JpaCommonEntityDaoImpl<T extends AbstractCoreEntity> implements JpaCommonEntityDao<T> {

    @PersistenceContext(unitName = "WebplazaPersistence")
    protected EntityManager entityManager;

    @Override
    public List<T> getAllEntity() {
        return entityManager.createQuery("SELECT e FROM " + getManagedClass().getSimpleName() + " e", getManagedClass()).getResultList();
    }

    @Override
    public void add(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(entityManager.find(getManagedClass(), id));
    }

    @Override
    public T getById(Long id) {
        return entityManager.find(getManagedClass(), id);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    protected abstract Class<T> getManagedClass();
}
