package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.PlazaDao;
import hu.szakdolgozat.entity.Plaza;

import javax.ejb.Stateless;

@Stateless
public class PlazaDaoImpl extends JpaCommonEntityDaoImpl<Plaza> implements PlazaDao {
    @Override
    protected Class<Plaza> getManagedClass() {
        return Plaza.class;
    }

    @Override
    public Plaza getPlazaByName(String name) {
        return entityManager.createQuery("select p from Plaza p where p.name = :name", Plaza.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
