package hu.szakdolgozat.mbean;

import hu.szakdolgozat.entity.*;
import hu.szakdolgozat.service.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ViewScoped
public class UserMBean implements Serializable {

    List<User> userList = new ArrayList<>();
    List<Order> orderList = new ArrayList<>();
    List<Product> prList = new ArrayList<>();
    List<Plaza> plazasList = new ArrayList<>();

    @Inject
    private UserService userService;

    @Inject
    private OrderService orderService;

    @Inject
    private ProductService productService;

    @Inject
    private ShopService shopService;
    @Inject
    private PlazaService plazaService;

    @PostConstruct
    public void init() {
        userList = userService.getAllEntity();
        orderList = orderService.getAllEntity();
        prList = new ArrayList<>();
        plazasList = plazaService.getAllEntity();
        for (var plaza : plazasList) {
            for (var shop : shopService.getShopsByPlazaId(plaza.getId())) {
                List<Product> products = shop.getProducts();
                prList.addAll(products);
            }
        }
        System.out.printf(prList.toString());
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
