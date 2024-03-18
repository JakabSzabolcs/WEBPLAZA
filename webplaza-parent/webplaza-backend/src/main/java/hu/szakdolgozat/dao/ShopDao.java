package hu.szakdolgozat.dao;

import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.entity.User;

import java.util.List;

public interface ShopDao extends JpaCommonEntityDao<Shop>{
    List<Shop> getShopsByPlazaId(Long id);
    List<Shop> getShopsByOwnerUser(User owner);

    List<Shop> getShopsByPlazaAndOwner(Plaza plaza, User owner);

    void remove(Shop shop);
}
