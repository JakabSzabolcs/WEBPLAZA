package hu.szakdolgozat.mbean.shop;


import hu.szakdolgozat.entity.Address;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.PlazaService;
import hu.szakdolgozat.service.ProductService;
import hu.szakdolgozat.service.ShopService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AllMyShopsMbean implements Serializable {

    private List<Shop> shopList = new ArrayList<>();
    private User loggedInUser;
    private Shop selectedShop = new Shop();
    private String newShopName;
    private String newShopDescription;
    private Long newPlazaId;
    private Address newPlazaAddress;
    private List<Plaza> allPlazaList;
    private List<Shop> selectedShops;
    private Map<Long, Integer> shopIdProductCountMap;


    @Inject
    private ShopService shopService;
    @Inject
    private PlazaService plazaService;
    @Inject
    private ProductService productService;

    @PostConstruct
    public void init() {
        load();
        initNew();
    }

    public void load() {
        allPlazaList = plazaService.getAllEntity();
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        shopList = shopService.getShopsByOwnerUser(loggedInUser);
        shopIdProductCountMap = shopList.stream().collect(
                Collectors.toMap(Shop::getId, shop -> shop.getProducts().size()));
    }

    public void newShop() {
        if (newShopName.isEmpty() || newPlazaId == null || newShopDescription.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba.", "Minden mező kitöltése kötelező."));
        } else {
            Shop newShop = new Shop();
            newShop.setName(newShopName);
            newShop.setDescription(newShopDescription);
            newShop.setUser(loggedInUser);
            newShop.setPlaza(plazaService.getById(newPlazaId));
            shopService.add(newShop);
            shopList = shopService.getShopsByOwnerUser(loggedInUser);
            initNew();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Siker.", "Sikeres hozzáadás."));
        }
    }

    public void editShop() {
        selectedShop.setPlaza(plazaService.getPlazaByName(selectedShop.getPlaza().getName()));
        shopService.update(selectedShop);
        shopList = shopService.getShopsByOwnerUser(loggedInUser);
        initNew();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Siker.", "Sikeres módosítás."));
    }

    public void initNew() {
        newShopName = "";
        newPlazaId = null;
        selectedShop = new Shop();
    }

    public void deleteShop() {
        shopService.delete(selectedShop);
        shopList = shopService.getShopsByOwnerUser(loggedInUser);
        initNew();
        load();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Siker.", "Sikeres törlés."));

    }


    public void viewProductsUnderShop(Shop shop) {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("currentShop", shop);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "productsUnderShop.xhtml?faces-redirect=true");
    }

    public Map<Long, Integer> getShopIdProductCountMap() {
        return shopIdProductCountMap;
    }

    public void setShopIdProductCountMap(Map<Long, Integer> shopIdProductCountMap) {
        this.shopIdProductCountMap = shopIdProductCountMap;
    }

    public List<Shop> getSelectedShops() {
        return selectedShops;
    }

    public void setSelectedShops(List<Shop> selectedShops) {
        this.selectedShops = selectedShops;
    }

    public void setPlazaAddressById() {
        newPlazaAddress = plazaService.getById(newPlazaId).getAddress();
    }

    public Address getNewPlazaAddress() {
        return newPlazaAddress;
    }

    public void setNewPlazaAddress(Address newPlazaAddress) {
        this.newPlazaAddress = newPlazaAddress;
    }

    public List<Plaza> getAllPlazaList() {
        return allPlazaList;
    }

    public void setAllPlazaList(List<Plaza> allPlazaList) {
        this.allPlazaList = allPlazaList;
    }

    public String getNewShopName() {
        return newShopName;
    }

    public void setNewShopName(String newShopName) {
        this.newShopName = newShopName;
    }

    public Long getNewPlazaId() {
        return newPlazaId;
    }

    public void setNewPlazaId(Long newPlazaId) {
        this.newPlazaId = newPlazaId;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public Shop getSelectedShop() {
        return selectedShop;
    }

    public void setSelectedShop(Shop selectedShop) {
        this.selectedShop = selectedShop;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getNewShopDescription() {
        return newShopDescription;
    }

    public void setNewShopDescription(String newShopDescription) {
        this.newShopDescription = newShopDescription;
    }
}
