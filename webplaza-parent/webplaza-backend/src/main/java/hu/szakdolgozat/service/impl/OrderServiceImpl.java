package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.service.OrderService;

import javax.ejb.Stateless;

@Stateless
public class OrderServiceImpl extends JpaCommonEntityServiceImpl<Order> implements OrderService {
}
