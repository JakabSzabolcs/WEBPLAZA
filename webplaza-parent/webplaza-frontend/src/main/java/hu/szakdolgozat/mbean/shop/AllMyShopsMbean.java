package hu.szakdolgozat.mbean.shop;


import hu.szakdolgozat.entity.*;
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
import java.util.HashMap;
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
    private Long newPlazaId;
    private Address newPlazaAddress;
    private List<Plaza> allPlazaList = new ArrayList<>();


    @Inject
    private ShopService shopService;
    @Inject
    private PlazaService plazaService;
    @Inject
    private ProductService productService;

    @PostConstruct
    public void init() {
        allPlazaList = plazaService.getAllEntity();
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        shopList = shopService.getShopsByOwnerUser(loggedInUser);
        initNew();
    }

    public void saveShop() {

        if (newShopName == null || newShopName.isEmpty() || newPlazaId == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "Nem adott meg nevet a boltjának."));
            return;
        }

        Shop newShop = new Shop();
        newShop.setName(newShopName);
        newShop.setUser(loggedInUser);
        newShop.setPlaza(plazaService.getById(newPlazaId));
        shopService.add(newShop);
        shopList = shopService.getShopsByOwnerUser(loggedInUser);
        initNew();

    }

    public void initNew() {
        newShopName = "";
        newPlazaId = null;
        selectedShop = new Shop();
    }

    public void onRowEdit(Shop shop) {
        shopService.update(shop);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sikeres szerkesztés"));
    }

    public void onRowCancel() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Szerkesztés megszakítva"));
    }

    public void deleteShop() {
        shopService.delete(selectedShop);
        shopList = shopService.getShopsByOwnerUser(loggedInUser);

    }

    public void viewProductsUnderShop(Shop shop) {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("currentShop", shop);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "productsUnderShop.xhtml?faces-redirect=true");
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
}
