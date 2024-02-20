package hu.szakdolgozat.dao;

import hu.szakdolgozat.entity.Shop;

import java.util.List;

public interface ShopDao extends JpaCommonEntityDao<Shop>{
    List<Shop> getShopsByPlazaId(Long id);
}
