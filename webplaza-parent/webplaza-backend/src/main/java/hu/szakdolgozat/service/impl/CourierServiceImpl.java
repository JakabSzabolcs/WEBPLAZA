package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.entity.Courier;
import hu.szakdolgozat.service.CourierService;

import javax.ejb.Stateless;

@Stateless
public class CourierServiceImpl extends JpaCommonEntityServiceImpl<Courier> implements CourierService {
}
