package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.ShopDao;
import hu.szakdolgozat.entity.Shop;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ShopDaoImpl extends JpaCommonEntityDaoImpl<Shop> implements ShopDao {
    @Override
    protected Class<Shop> getManagedClass() {
        return Shop.class;
    }

    @Override
    public List<Shop> getShopsByPlazaId(Long id) {
        return entityManager.createQuery("SELECT s FROM Shop s WHERE s.plaza.id = :id", Shop.class)
                .setParameter("id", id)
                .getResultList();
    }
}
