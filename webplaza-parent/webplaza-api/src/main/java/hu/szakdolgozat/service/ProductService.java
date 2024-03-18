package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.Shop;

import java.util.List;

public interface ProductService extends JpaCommonEntityService<Product>{
    List<Product> getProductsByShop(Shop shop);

    List<Product> getProductByOrder(Order order);
}
