package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.CourierDao;
import hu.szakdolgozat.entity.Courier;

import javax.ejb.Stateless;

@Stateless
public class CourierDaoImpl extends JpaCommonEntityDaoImpl<Courier> implements CourierDao {
    @Override
    protected Class<Courier> getManagedClass() {
        return Courier.class;
    }
}
