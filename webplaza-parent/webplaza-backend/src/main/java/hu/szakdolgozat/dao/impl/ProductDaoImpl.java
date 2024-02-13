package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.ProductDao;
import hu.szakdolgozat.entity.Product;

import javax.ejb.Stateless;

@Stateless
public class ProductDaoImpl extends JpaCommonEntityDaoImpl<Product> implements ProductDao {
    @Override
    protected Class<Product> getManagedClass() {
        return Product.class;
    }
}
