package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.entity.User;

import java.util.List;

public interface ShopService extends JpaCommonEntityService<Shop>{


    List<Shop> getShopsByPlazaId(Long id);

    List<Shop> getShopsByOwnerUser(User owner);

    List<Shop> getShopsByPlazaAndOwner(Plaza plaza, User owner);

    void remove(Shop shop);


}
