package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.ShopDao;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.entity.User;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.Collections;
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

    @Override
    public List<Shop> getShopsByOwnerUser(User owner) {
        try {
            return entityManager.createQuery("SELECT s FROM Shop s WHERE s.user.id = :id", Shop.class)
                    .setParameter("id", owner.getId())
                    .getResultList();

        } catch (NoResultException e) {
            return Collections.emptyList();

        }
    }

    @Override
    public List<Shop> getShopsByPlazaAndOwner(Plaza plaza, User owner) {

        if (plaza == null) {
            return getShopsByOwnerUser(owner);
        }

        return entityManager.createQuery("SELECT s FROM Shop s WHERE s.plaza.id = :plazaId AND s.user.id = :userId", Shop.class)
                .setParameter("plazaId", plaza.getId())
                .setParameter("userId", owner.getId())
                .getResultList();

    }

    @Override
    public void remove(Shop shop) {
        //delete the pruducts under the shop too
        entityManager.createQuery("DELETE FROM Product p WHERE p.shop.id = :id")
                .setParameter("id", shop.getId())
                .executeUpdate();


        entityManager.remove(shop);
    }

}