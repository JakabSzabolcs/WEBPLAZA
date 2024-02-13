package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.ShopDao;
import hu.szakdolgozat.entity.Shop;

import javax.ejb.Stateless;

@Stateless
public class ShopDaoImpl extends JpaCommonEntityDaoImpl<Shop> implements ShopDao {
    @Override
    protected Class<Shop> getManagedClass() {
        return Shop.class;
    }
}
