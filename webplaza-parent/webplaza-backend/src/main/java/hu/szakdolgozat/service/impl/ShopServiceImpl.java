package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.dao.ShopDao;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.ShopService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ShopServiceImpl extends JpaCommonEntityServiceImpl<Shop> implements ShopService {

    @Inject
    private ShopDao shopDao;

    @Override
    public List<Shop> getShopsByPlazaId(Long id) {
        return shopDao.getShopsByPlazaId(id);
    }

    @Override
    public List<Shop> getShopsByOwnerUser(User owner) {
        return shopDao.getShopsByOwnerUser(owner);
    }

    @Override
    public List<Shop> getShopsByPlazaAndOwner(Plaza plaza, User owner) {
        return shopDao.getShopsByPlazaAndOwner(plaza, owner);
    }

    @Override
    public void remove(Shop shop) {
        shopDao.remove(shop);
    }


}

