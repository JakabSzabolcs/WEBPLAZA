package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends JpaCommonEntityService<Order>{
    List<Order> getWaitingOrdersByPlaza(Plaza plaza);

    List<Order> getOrdersByUser(User user);

    BigDecimal getOrderSum(Order order);

}
