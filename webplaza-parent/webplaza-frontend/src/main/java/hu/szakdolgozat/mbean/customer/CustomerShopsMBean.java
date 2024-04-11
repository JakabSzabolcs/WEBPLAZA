package hu.szakdolgozat.mbean.customer;

import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.service.ProductService;
import hu.szakdolgozat.service.ShopService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class CustomerShopsMBean implements Serializable {

    @Inject
    private ShopService shopService;

    @Inject
    private ProductService productService;


    private List<Shop> shopList;
    private Plaza currentPlaza;
    private Map<Long, Integer> shopIdProductCountMap;


    @PostConstruct
    public void init() {
        currentPlaza = (Plaza) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPlaza");
        shopList = shopService.getShopsByPlazaId(currentPlaza.getId());
        shopIdProductCountMap = shopList.stream()
                .collect(Collectors.toMap(Shop::getId, shop -> productService.getProductsByShop(shop).size()));

    }

    public void onShopClick(Shop shop) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedShop", shop);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "customerProducts.xhtml?faces-redirect=true");

    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public Plaza getCurrentPlaza() {
        return currentPlaza;
    }

    public void setCurrentPlaza(Plaza currentPlaza) {
        this.currentPlaza = currentPlaza;
    }

    public Map<Long, Integer> getShopIdProductCountMap() {
        return shopIdProductCountMap;
    }

    public void setShopIdProductCountMap(Map<Long, Integer> shopIdProductCountMap) {
        this.shopIdProductCountMap = shopIdProductCountMap;
    }
}
