package hu.szakdolgozat.mbean.shop;

import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ProductsUnderShopMBean implements Serializable {

    private List<Product> products = new ArrayList<>();
    private Product selectedProduct = new Product();
    private Shop currentShop = new Shop();

    @Inject
    private ProductService productService;


    @PostConstruct
    public void init() {
        currentShop = (Shop) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentShop");
        products = productService.getProductsByShop(currentShop);
    }

    public void deleteProduct() {
        productService.delete(selectedProduct);
        products = productService.getProductsByShop(currentShop);
    }

    public void updateProduct() {
        productService.update(selectedProduct);
        products = productService.getProductsByShop(currentShop);
    }

    public void saveProduct() {
        selectedProduct.setShop(currentShop);
        productService.add(selectedProduct);
        products = productService.getProductsByShop(currentShop);
    }

    public void initNew() {
        selectedProduct = new Product();
    }


    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Shop getCurrentShop() {
        return currentShop;
    }

    public void setCurrentShop(Shop currentShop) {
        this.currentShop = currentShop;
    }
}
