package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.dao.ProductDao;
import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.service.ProductService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductServiceImpl extends JpaCommonEntityServiceImpl<Product> implements ProductService {

    @Inject
    private ProductDao productDao;

    @Override
    public List<Product> getProductsByShop(Shop shop) {
        return productDao.getProductsByShop(shop);

    }

    @Override
    public List<Product> getProductByOrder(Order order) {
        return productDao.getProductsByOrder(order);
    }
}
