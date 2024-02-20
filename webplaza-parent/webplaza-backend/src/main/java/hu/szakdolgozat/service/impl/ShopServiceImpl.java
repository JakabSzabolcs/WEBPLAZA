package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.dao.ShopDao;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.service.ShopService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class ShopServiceImpl extends JpaCommonEntityServiceImpl<Shop> implements ShopService {

    @Inject
    private ShopDao shopDao;

    @Override
    public List<Shop> getShopsByPlazaId(Long id) {
        return shopDao.getShopsByPlazaId(id);

    }
}
