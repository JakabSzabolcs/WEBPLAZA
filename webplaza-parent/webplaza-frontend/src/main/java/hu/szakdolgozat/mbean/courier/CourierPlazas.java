package hu.szakdolgozat.mbean.courier;

import hu.szakdolgozat.entity.Address;
import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.OrderService;
import hu.szakdolgozat.service.PlazaService;

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
public class CourierPlazas implements Serializable {

    @Inject
    private OrderService orderService;

    @Inject
    private PlazaService plazaService;

    private List<Plaza> plazaList = new ArrayList<>();
    private Map<Long, List<Order>> waintingOrderCountByPlaza;
    private List<Plaza> filteredPlazaList = new ArrayList<>();
    private List<Order> orderListByPlaza = new ArrayList<>();
    private Address inputAddress = new Address();
    User loggedInUser = new User();


    @PostConstruct
    public void init() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        plazaList = plazaService.getAllEntity();
        waintingOrderCountByPlaza = plazaList.stream().collect(Collectors.toMap(Plaza::getId, plaza -> orderService.getWaitingOrdersByPlaza(plaza)));
    }

    public int getWaitingOrdersByPlaza(Plaza plaza) {
        return orderService.getWaitingOrdersByPlaza(plaza).size();
    }

    public void refreshPlazaList() {
        filteredPlazaList = new ArrayList<>();
        filteredPlazaList.addAll(plazaList.stream().filter(plaza -> {
            if (inputAddress.getPostalCode() != null && !inputAddress.getPostalCode().isEmpty()) {
                return Objects.equals(inputAddress.getPostalCode(), plaza.getAddress().getPostalCode());
            }
            return true;
        }).filter(plaza -> {
            if (inputAddress.getCity() != null && !inputAddress.getCity().isEmpty()) {
                return Objects.equals(inputAddress.getCity(), plaza.getAddress().getCity());
            }
            return true;
        }).filter(plaza -> getWaitingOrdersByPlaza(plaza) > 0).collect(Collectors.toList()));
    }

    public void onPlazaClick(Plaza plaza) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPlaza", plaza);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "courierOrders.xhtml?faces-redirect=true");

    }


    public Map<Long, List<Order>> getWaintingOrderCountByPlaza() {
        return waintingOrderCountByPlaza;
    }

    public void setWaintingOrderCountByPlaza(Map<Long, List<Order>> waintingOrderCountByPlaza) {
        this.waintingOrderCountByPlaza = waintingOrderCountByPlaza;
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

    public List<Order> getOrderListByPlaza() {
        return orderListByPlaza;
    }

    public void setOrderListByPlaza(List<Order> orderListByPlaza) {
        this.orderListByPlaza = orderListByPlaza;
    }
}
