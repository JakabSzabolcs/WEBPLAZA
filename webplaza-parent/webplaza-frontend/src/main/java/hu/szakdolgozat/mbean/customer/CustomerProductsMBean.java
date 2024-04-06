package hu.szakdolgozat.mbean.customer;

import hu.szakdolgozat.entity.*;
import hu.szakdolgozat.enums.OrderState;
import hu.szakdolgozat.service.OrderService;
import hu.szakdolgozat.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class CustomerProductsMBean implements Serializable {

    private Shop currentShop;

    private List<Product> productList;

    private String postalCodeIn;
    private String streetIn;
    private String houseNumberIn;

    private final List<String> streetType = List.of("utca", "út", "tér", "körút", "sétány", "sor", "dűlő", "lejáró", "park", "parkja", "tanya", "völgy", "rét", "erdő", "domb", "hegy", "sziget");
    private String streetTypeSelected;
    private Order currentOrder = new Order();
    private User loggedInUser;

    private Map<Long, Order> plazaIdOrderMap = new HashMap<>();

    @Inject
    private ProductService productService;
    @Inject
    private OrderService orderService;


    @PostConstruct
    public void init() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        currentShop = (Shop) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedShop");
        productList = productService.getProductsByShop(currentShop);

        plazaIdOrderMap = (Map<Long, Order>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("plazaOrderMap");
        if (plazaIdOrderMap == null) {
            plazaIdOrderMap = new HashMap<>();
        }
        currentOrder = plazaIdOrderMap.get(currentShop.getPlaza().getId());
        if (currentOrder == null) {
            currentOrder = new Order();
            currentOrder.setProducts(List.of());
            plazaIdOrderMap.put(currentShop.getPlaza().getId(), currentOrder);

        }
    }

    public void addProductToOrder(Product product) {
        List<Product> products = new ArrayList<>(currentOrder.getProducts());
        products.add(product);
        currentOrder.setProducts(products);

        saveOrdertoSession();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres rendelés", "A termék a kosárba került"));

    }

    public void backToShops() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedShop");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPlaza", currentShop.getPlaza());
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "customerShops.xhtml?faces-redirect=true");
    }


    public void placingOrder() {
        Order order = new Order();
        order.setCustomer(loggedInUser);

        List<Product> products = new ArrayList<>();
        for (Product product : currentOrder.getProducts()) {
            Product managedProduct = productService.getById(product.getId());
            products.add(managedProduct);
        }

        order.setProducts(products);
        order.setOrderState(OrderState.NEW);
        order.setDeliveryAddress(new Address(postalCodeIn, currentShop.getPlaza().getAddress().getCity(), streetIn, houseNumberIn));
        orderService.add(order);

        currentOrder = new Order();
        currentOrder.setProducts(List.of());
        saveOrdertoSession();
    }


    private void saveOrdertoSession() {
        plazaIdOrderMap.put(currentShop.getPlaza().getId(), currentOrder);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("plazaOrderMap", plazaIdOrderMap);
    }

    public void removeProductFromOrder(Product product) {
        currentOrder.getProducts().remove(product);
        saveOrdertoSession();
    }

    public BigDecimal getCurrentOrderSum() {
        return orderService.getOrderSum(currentOrder);

    }

    public Shop getCurrentShop() {
        return currentShop;
    }

    public void setCurrentShop(Shop currentShop) {
        this.currentShop = currentShop;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Map<Long, Order> getPlazaIdOrderMap() {
        return plazaIdOrderMap;
    }

    public void setPlazaIdOrderMap(Map<Long, Order> plazaIdOrderMap) {
        this.plazaIdOrderMap = plazaIdOrderMap;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<String> getStreetType() {
        return streetType;
    }

    public String getStreetTypeSelected() {
        return streetTypeSelected;
    }

    public void setStreetTypeSelected(String streetTypeSelected) {
        this.streetTypeSelected = streetTypeSelected;
    }

    public String getPostalCodeIn() {
        return postalCodeIn;
    }

    public void setPostalCodeIn(String postalCodeIn) {
        this.postalCodeIn = postalCodeIn;
    }

    public String getStreetIn() {
        return streetIn;
    }

    public void setStreetIn(String streetIn) {
        this.streetIn = streetIn;
    }

    public String getHouseNumberIn() {
        return houseNumberIn;
    }

    public void setHouseNumberIn(String houseNumberIn) {
        this.houseNumberIn = houseNumberIn;
    }
}
