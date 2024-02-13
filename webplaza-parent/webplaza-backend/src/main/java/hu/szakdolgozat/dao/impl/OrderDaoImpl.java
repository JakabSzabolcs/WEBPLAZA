package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.OrderDao;
import hu.szakdolgozat.entity.Order;

import javax.ejb.Stateless;

@Stateless
public class OrderDaoImpl extends JpaCommonEntityDaoImpl<Order> implements OrderDao {
    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }
}
