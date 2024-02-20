package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.Shop;

import java.util.List;

public interface ShopService extends JpaCommonEntityService<Shop>{


    List<Shop> getShopsByPlazaId(Long id);

}
