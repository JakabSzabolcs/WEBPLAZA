package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.OrderDao;
import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.User;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class OrderDaoImpl extends JpaCommonEntityDaoImpl<Order> implements OrderDao {

    private static final String SELECT_WAITING_ORDERS_BY_PLAZA = "SELECT\n" + "    distinct (o.id)\n" + "FROM orders o\n" + "         INNER JOIN orders_product op ON o.id = op.order_id\n" + "         INNER JOIN product p ON op.products_id = p.id\n" + "INNER JOIN shop s on p.shop_id = s.id\n" + "WHERE s.plaza_id = :plazaId\n" + "AND o.order_state = 'WAITING'";

    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }

    @Override
    public List<Order> getWaitingOrdersByPlaza(Plaza plaza) {
        try {
            List<Integer> orderIds = entityManager.createNativeQuery(SELECT_WAITING_ORDERS_BY_PLAZA).setParameter("plazaId", plaza.getId()).getResultList();
            List<Order> orders = new ArrayList<>();
            for (Integer orderId : orderIds) {
                Order order = entityManager.find(Order.class, Long.valueOf(orderId));
                orders.add(order);
            }
            return orders;
        } catch (NoResultException e) {
            return Collections.emptyList();
        }

    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.customer = :user", Order.class).setParameter("user", user).getResultList();
    }

    @Override
    public BigDecimal getOrderSum(Order order) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : order.getProducts()) {
            BigDecimal price = BigDecimal.valueOf(product.getPrice());
            switch (product.getCurrency()) {
                case HUF:
                    sum = sum.add(price);
                    break;
                case EUR:
                    sum = sum.add(price.multiply(BigDecimal.valueOf(380)));
                    break;
                case USD:
                    sum = sum.add(price.multiply(BigDecimal.valueOf(360)));
                    break;
            }
        }
        return sum;


    }
}
