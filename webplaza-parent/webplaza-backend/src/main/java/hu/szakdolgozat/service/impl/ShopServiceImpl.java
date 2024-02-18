package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.service.ShopService;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ShopServiceImpl extends JpaCommonEntityServiceImpl<Shop> implements ShopService {
}
