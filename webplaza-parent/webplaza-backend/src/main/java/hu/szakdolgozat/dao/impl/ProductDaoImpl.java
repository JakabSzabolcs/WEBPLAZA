package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.ProductDao;
import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.Shop;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductDaoImpl extends JpaCommonEntityDaoImpl<Product> implements ProductDao {

    private static final String GET_PRODUCTS_BY_ORDER = "SELECT p.* FROM product p " +
            "JOIN orders_product op ON p.id = op.products_id " +
            "WHERE op.order_id = :order_id";

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

    @Override
    public List<Product> getProductsByOrder(Order order) {
        return entityManager.createNativeQuery(GET_PRODUCTS_BY_ORDER, Product.class)
                .setParameter("order_id", order.getId())
                .getResultList();

    }
}
