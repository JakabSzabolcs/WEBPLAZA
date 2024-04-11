package hu.szakdolgozat.mbean.customer;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.OrderService;
import hu.szakdolgozat.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CustomerOldOrdersMBean implements Serializable {

    private List<Order> myOrders = new ArrayList<>();
    private User loggedInUser;

    @Inject
    private ProductService productService;

    @Inject
    private OrderService orderService;

    @PostConstruct
    private void init() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        myOrders = orderService.getOrdersByUser(loggedInUser);
    }

    public BigDecimal getOrderSum(Order order) {
        return orderService.getOrderSum(order);

    }

    public List<Product> getProductsByOrder(Order order) {
        return productService.getProductByOrder(order);
    }

    public List<Order> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<Order> myOrders) {
        this.myOrders = myOrders;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
