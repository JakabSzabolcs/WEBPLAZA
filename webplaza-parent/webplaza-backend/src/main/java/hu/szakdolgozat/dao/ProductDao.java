package hu.szakdolgozat.dao;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.Shop;

import java.util.List;

public interface ProductDao extends JpaCommonEntityDao<Product>{
    List<Product> getProductsByShop(Shop shop);

    List<Product> getProductsByOrder(Order order);
}
