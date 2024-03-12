package hu.szakdolgozat.mbean.shop;

import hu.szakdolgozat.entity.Address;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.PlazaService;
import hu.szakdolgozat.service.ShopService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ShopOwnerPlazasMBean implements Serializable {

    @Inject
    private ShopService shopService;

    @Inject
    private PlazaService plazaService;

    private List<Plaza> plazaList = new ArrayList();
    private Map<Long, List<Shop>> shopCountByOwner;
    private List<Plaza> filteredPlazaList = new ArrayList<>();
    private List<Shop> shopListByLogedInUser = new ArrayList<>();
    private Address inputAddress = new Address();
    User loggedInUser = new User();


    @PostConstruct
    public void init() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        plazaList = plazaService.getAllEntity();
        shopListByLogedInUser = shopService.getShopsByOwnerUser(loggedInUser);

        shopCountByOwner = shopListByLogedInUser.stream().collect(Collectors.groupingBy(shop -> shop.getPlaza().getId()));
        System.out.println("shopCountByOwner: " + shopCountByOwner);
    }

    public void refreshPlazaList() {
        filteredPlazaList = new ArrayList<>();
        filteredPlazaList.addAll(plazaList
                .stream()
                .filter(plaza -> {
                    if (inputAddress.getPostalCode() != null && !inputAddress.getPostalCode().isEmpty()) {
                        return Objects.equals(inputAddress.getPostalCode(), plaza.getAddress().getPostalCode());
                    }
                    return true;
                })
                .filter(plaza -> {
                    if (inputAddress.getCity() != null && !inputAddress.getCity().isEmpty()) {
                        return Objects.equals(inputAddress.getCity(), plaza.getAddress().getCity());
                    }
                    return true;
                }).collect(Collectors.toList()));
    }

    public void onPlazaClick(Plaza plaza) {
        //redirect to shopsUnderPlaza.xhtml with the selected plaza
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPlaza", plaza);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "shopsUnderPlaza.xhtml?faces-redirect=true");

    }

    public Map<Long, List<Shop>> getShopCountByOwner() {
        return shopCountByOwner;
    }

    public void setShopCountByOwner(Map<Long, List<Shop>> shopCountByOwner) {
        this.shopCountByOwner = shopCountByOwner;
    }

    public List<Plaza> getFilteredPlazaList() {
        return filteredPlazaList;
    }

    public void setFilteredPlazaList(List<Plaza> filteredPlazaList) {
        this.filteredPlazaList = filteredPlazaList;
    }

    public Address getInputAddress() {
        return inputAddress;
    }

    public void setInputAddress(Address inputAddress) {
        this.inputAddress = inputAddress;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<Plaza> getPlazaList() {
        return plazaList;
    }

    public void setPlazaList(List<Plaza> plazaList) {
        this.plazaList = plazaList;
    }

    public List<Shop> getShopListByLogedInUser() {
        return shopListByLogedInUser;
    }

    public void setShopListByLogedInUser(List<Shop> shopListByLogedInUser) {
        this.shopListByLogedInUser = shopListByLogedInUser;
    }
}
