package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.dao.OrderDao;
import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.OrderService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class OrderServiceImpl extends JpaCommonEntityServiceImpl<Order> implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public List<Order> getWaitingOrdersByPlaza(Plaza plaza) {
        return orderDao.getWaitingOrdersByPlaza(plaza);
    }


    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderDao.getOrdersByUser(user);
    }

    @Override
    public BigDecimal getOrderSum(Order order) {
        return orderDao.getOrderSum(order);
    }
}
