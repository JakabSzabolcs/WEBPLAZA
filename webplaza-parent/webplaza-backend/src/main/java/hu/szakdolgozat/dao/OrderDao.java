package hu.szakdolgozat.dao;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao extends JpaCommonEntityDao<Order>{
    List<Order> getNewOrdersByPlaza(Plaza plaza);

    List<Order> getOrdersByUser(User user);

    BigDecimal getOrderSum(Order order);

    boolean isCourierOccupied(User user);

    Order getActiveOrderByCourier(User user);

}
