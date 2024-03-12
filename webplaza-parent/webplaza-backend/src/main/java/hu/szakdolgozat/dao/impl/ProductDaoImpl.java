package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.ProductDao;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.Shop;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductDaoImpl extends JpaCommonEntityDaoImpl<Product> implements ProductDao {
    @Override
    protected Class<Product> getManagedClass() {
        return Product.class;
    }

    @Override
    public List<Product> getProductsByShop(Shop shop) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.shop = :shop", Product.class)
                .setParameter("shop", shop)
                .getResultList();
    }
}
