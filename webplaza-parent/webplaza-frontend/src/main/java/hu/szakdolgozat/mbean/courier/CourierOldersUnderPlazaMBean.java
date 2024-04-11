package hu.szakdolgozat.mbean.courier;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.enums.OrderState;
import hu.szakdolgozat.service.OrderService;
import hu.szakdolgozat.service.ProductService;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class CourierOldersUnderPlazaMBean implements Serializable {

    private Plaza currentPlaza;
    private List<Order> orderList;
    private Order selectedOrder;
    private User loggedInUser;

    @Inject
    private OrderService orderService;

    @Inject
    private ProductService productService;

    @PostConstruct
    public void init() {
        currentPlaza = (Plaza) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPlaza");
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        refreshOrderList();
    }

    public void acceptOrder() {
        if (selectedOrder.getCourier() != null) {
            return;
        }
        if (orderService.isCourierOccupied(loggedInUser)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Már van aktív rendelésed!", null));
            return;
        }
        selectedOrder.setCourier(loggedInUser);
        selectedOrder.setOrderState(OrderState.IN_PROGRESS);
        orderService.update(selectedOrder);
        refreshOrderList();
        FacesContext.getCurrentInstance().getApplication()
                .getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "courierOrdersUnderPlaza.xhtml?faces-redirect=true");

    }


    public void refreshOrderList() {
        selectedOrder = new Order();
        orderList = orderService.getNewOrdersByPlaza(currentPlaza);
    }

    public BigDecimal getOrderSum(Order order) {
        return orderService.getOrderSum(order);

    }

    public List<Product> getProductsByOrder(Order order) {
        return productService.getProductByOrder(order);
    }

    public Plaza getCurrentPlaza() {
        return currentPlaza;
    }

    public void setCurrentPlaza(Plaza currentPlaza) {
        this.currentPlaza = currentPlaza;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
}
