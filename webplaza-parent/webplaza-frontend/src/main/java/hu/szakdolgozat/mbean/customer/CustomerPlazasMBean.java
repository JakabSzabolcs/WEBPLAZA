package hu.szakdolgozat.mbean.customer;

import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.service.PlazaService;
import hu.szakdolgozat.service.ShopService;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
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
public class CustomerPlazasMBean implements Serializable {

    private String searchPostalCode;
    private String searchCity;
    private List<Plaza> plazaList = new ArrayList<>();
    private List<Plaza> filteredPlazaList = new ArrayList<>();
    private Map<Long, Integer> plazaIdShopCountMap;
    private Map<Long, Integer> plazaIdProductCountMap;

    @Inject
    private PlazaService plazaService;
    @Inject
    private ShopService shopService;

    @PostConstruct
    public void init() {
        load();
    }

    public void load() {
        plazaList = plazaService.getAllEntity();
        plazaIdShopCountMap = plazaList.stream().collect(Collectors.toMap(Plaza::getId, plaza -> shopService.getShopsByPlazaId(plaza.getId()).size()));
        plazaIdProductCountMap = plazaList.stream().collect(Collectors.toMap(Plaza::getId, plaza -> shopService.getShopsByPlazaId(plaza.getId()).stream().map(shop -> shop.getProducts().size()).reduce(0, Integer::sum)));
        refreshPlazaList();
    }


    public void refreshPlazaList() {
        filteredPlazaList = new ArrayList<>();
        filteredPlazaList.addAll(plazaList.stream().filter(plaza -> {
            if (searchPostalCode != null && !searchPostalCode.isEmpty()) {
                return searchPostalCode.equals(plaza.getAddress().getPostalCode());
            }
            return true;
        }).filter(plaza -> {
            if (searchCity != null && !searchCity.isEmpty()) {
                return searchCity.equals(plaza.getAddress().getCity());
            }
            return true;
        }).filter(plaza -> plazaIdProductCountMap.get(plaza.getId()) > 0).collect(Collectors.toList()));
        PrimeFaces.current().executeScript("PF('plazasForm').update();");
    }

    public void onPlazaClick(Plaza plaza) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPlaza", plaza);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "customerShops.xhtml?faces-redirect=true");

    }

    public String getSearchPostalCode() {
        return searchPostalCode;
    }

    public void setSearchPostalCode(String searchPostalCode) {
        this.searchPostalCode = searchPostalCode;
    }

    public String getSearchCity() {
        return searchCity;
    }

    public void setSearchCity(String searchCity) {
        this.searchCity = searchCity;
    }

    public List<Plaza> getPlazaList() {
        return plazaList;
    }

    public void setPlazaList(List<Plaza> plazaList) {
        this.plazaList = plazaList;
    }

    public List<Plaza> getFilteredPlazaList() {
        return filteredPlazaList;
    }

    public void setFilteredPlazaList(List<Plaza> filteredPlazaList) {
        this.filteredPlazaList = filteredPlazaList;
    }

    public Map<Long, Integer> getPlazaIdShopCountMap() {
        return plazaIdShopCountMap;
    }

    public void setPlazaIdShopCountMap(Map<Long, Integer> plazaIdShopCountMap) {
        this.plazaIdShopCountMap = plazaIdShopCountMap;
    }

    public Map<Long, Integer> getPlazaIdProductCountMap() {
        return plazaIdProductCountMap;
    }

    public void setPlazaIdProductCountMap(Map<Long, Integer> plazaIdProductCountMap) {
        this.plazaIdProductCountMap = plazaIdProductCountMap;
    }
}
