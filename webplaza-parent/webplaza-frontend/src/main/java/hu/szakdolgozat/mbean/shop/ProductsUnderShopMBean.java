package hu.szakdolgozat.mbean.shop;

import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.enums.Currency;
import hu.szakdolgozat.enums.MeasureUnit;
import hu.szakdolgozat.enums.ProductCategory;
import hu.szakdolgozat.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
    private List<ProductCategory> categories = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();

    private List<MeasureUnit> mennyEgyegek = new ArrayList<>();

    @Inject
    private ProductService productService;


    @PostConstruct
    public void init() {
        load();
    }

    public void load() {
        currentShop = (Shop) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentShop");
        products = productService.getProductsByShop(currentShop);
        categories = List.of(ProductCategory.values());
        currencies = List.of(Currency.values());
        mennyEgyegek = List.of(MeasureUnit.values());
        initNew();
    }

    public void deleteProduct() {
        productService.delete(selectedProduct);
        products = productService.getProductsByShop(currentShop);
        initNew();
    }

    public void save() {

        if (selectedProduct.getName().isEmpty() || selectedProduct.getCategory() == null || selectedProduct.getPrice() == null || selectedProduct.getMeasureUnit() == null || selectedProduct.getCurrency() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba.", "Minden mező kitöltése kötelező."));
            return;
        }


        if (selectedProduct.getId() == null) {
            selectedProduct.setShop(currentShop);
            productService.add(selectedProduct);
        } else {
            selectedProduct.setShop(currentShop);
            productService.update(selectedProduct);
        }
        products = productService.getProductsByShop(currentShop);
        load();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Siker.", "Sikeres mentés."));


    }


    public void initNew() {
        selectedProduct = new Product();
    }


    public List<MeasureUnit> getMennyEgyegek() {
        return mennyEgyegek;
    }

    public void setMennyEgyegek(List<MeasureUnit> mennyEgyegek) {
        this.mennyEgyegek = mennyEgyegek;
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

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }
}
