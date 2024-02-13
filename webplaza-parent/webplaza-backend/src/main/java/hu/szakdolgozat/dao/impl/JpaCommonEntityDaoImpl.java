package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.JpaCommonEntityDao;
import hu.szakdolgozat.entity.CoreEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class JpaCommonEntityDaoImpl<T extends CoreEntity> implements JpaCommonEntityDao<T> {

    @Inject
    @PersistenceContext(unitName = "WebplazaPersistence")
    private EntityManager entityManager;

    @Override
    public List<T> getAllEntity(T entity) {
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
