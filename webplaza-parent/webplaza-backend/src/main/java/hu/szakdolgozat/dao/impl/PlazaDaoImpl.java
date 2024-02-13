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
}
