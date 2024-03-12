package hu.szakdolgozat.mbean.shop;

import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.JpaCommonEntityService;
import hu.szakdolgozat.service.ShopService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ViewScoped
public class ShopsUnderPlaza implements Serializable {

    private List<Shop> shopList = new ArrayList<>();
    private User loggedInUser;
    private Plaza currentPlaza;
    private Shop selectedShop;
    private String newShopName;

    @Inject
    private JpaCommonEntityService<Shop> commonService;

    @Inject
    private ShopService shopService;

    @PostConstruct
    public void init() {
        currentPlaza = (Plaza) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPlaza");
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        load();
        initNew();
    }

    public void saveShop() {
        initNew();

        if (newShopName == null || newShopName.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "Nem adott meg nevet a boltjának."));
            return;
        }

        if (selectedShop.getId() == null) {
            selectedShop.setName(newShopName);
            selectedShop.setUser(loggedInUser);
            selectedShop.setPlaza(currentPlaza);
            shopService.add(selectedShop);
        } else {
            shopService.update(selectedShop);
        }
        load();
        initNew();
    }


    public void deleteShop() {
        shopService.delete(selectedShop);
        load();
        initNew();
    }

    public void onRowEdit(RowEditEvent<Shop> event) {
        shopService.update(event.getObject());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Megváltozott", event.getObject().getName() + "bolt sikeresen megváltozott."));
    }

    public void onRowCancel(RowEditEvent<Shop> event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mégse", event.getObject().getName() + "bolt módosítása megszakítva."));
    }

    public void initNew() {
        selectedShop = new Shop();
    }

    public void load() {
        shopList = shopService.getShopsByPlazaAndOwner(currentPlaza, loggedInUser);
    }

    public Shop getSelectedShop() {
        return selectedShop;
    }

    public String getNewShopName() {
        return newShopName;
    }

    public void setNewShopName(String newShopName) {
        this.newShopName = newShopName;
    }

    public void setSelectedShop(Shop selectedShop) {
        this.selectedShop = selectedShop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Plaza getCurrentPlaza() {
        return currentPlaza;
    }

    public void setCurrentPlaza(Plaza currentPlaza) {
        this.currentPlaza = currentPlaza;
    }
}
