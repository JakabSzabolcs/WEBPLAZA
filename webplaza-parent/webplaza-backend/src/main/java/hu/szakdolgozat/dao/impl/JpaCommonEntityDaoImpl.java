package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.JpaCommonEntityDao;
import hu.szakdolgozat.entity.CoreEntity;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class JpaCommonEntityDaoImpl<T extends CoreEntity> implements JpaCommonEntityDao<T> {

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
        entityManager.remove(getById(id));
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
