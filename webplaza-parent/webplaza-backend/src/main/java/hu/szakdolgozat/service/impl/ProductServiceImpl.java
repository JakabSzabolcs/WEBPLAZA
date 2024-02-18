package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.service.ProductService;

import javax.ejb.Stateless;

@Stateless
public class ProductServiceImpl extends JpaCommonEntityServiceImpl<Product> implements ProductService {
}
