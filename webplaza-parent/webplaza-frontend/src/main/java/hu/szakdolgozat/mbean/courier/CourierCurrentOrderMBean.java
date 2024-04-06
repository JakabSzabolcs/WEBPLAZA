package hu.szakdolgozat.mbean.courier;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.Product;
import hu.szakdolgozat.entity.Shop;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.enums.OrderState;
import hu.szakdolgozat.service.OrderService;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class CourierCurrentOrderMBean implements Serializable {
    private User loggedInUser;
    private Order currentOrder;
    private List<Shop> shopList; //Azok a boltok amik érintik a rendelés termékeit

    private TreeNode[] selectedNodes;
    private TreeNode<Order> orderRoot = new DefaultTreeNode<>();


    @Inject
    private OrderService orderService;

    @PostConstruct
    public void init() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        currentOrder = orderService.getActiveOrderByCourier(loggedInUser);
        if (currentOrder != null) {
            shopList = currentOrder.getProducts().stream().map(Product::getShop).distinct().collect(Collectors.toList());
            loadTreeTable();
        }
    }

    private void loadTreeTable() {
        orderRoot = new DefaultTreeNode<>(currentOrder, null);
        orderRoot.setExpanded(true);
        addShopNodesUnderOrderNode();
    }


    private void addShopNodesUnderOrderNode() {
        for (Shop shop : shopList) {
            TreeNode<Shop> shopNode = new DefaultTreeNode<>(shop, orderRoot);
            shopNode.setExpanded(true);
            createProductNodesUnderShopNode(shop, shopNode);
        }
    }

    private void createProductNodesUnderShopNode(Shop shop, TreeNode<Shop> shopNode) {
        List<Product> products = currentOrder.getProducts().stream().filter(product -> product.getShop().equals(shop)).collect(Collectors.toList());
        for (Product product : products) {
            TreeNode<Product> productNode = new DefaultTreeNode<>(product, shopNode);
        }
    }

    public void toDeliver() {

        if (selectedNodes.length == shopList.size() + currentOrder.getProducts().size()) {
            currentOrder.setOrderState(OrderState.UNDER_DELIVERY);
            orderService.update(currentOrder);
            loadTreeTable();

            PrimeFaces.current().executeScript("PF('deliveryDialog').show()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiányoznak termékek a kiszállításhoz", ""));
        }
    }

    public void delivered() {
        currentOrder.setOrderState(OrderState.DONE);
        currentOrder.setDeliveryDate(LocalDateTime.now());
        orderService.update(currentOrder);
        PrimeFaces.current().executeScript("PF('deliveryDialog').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres kiszállítás", ""));
        init();
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

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public TreeNode<Order> getOrderRoot() {
        return orderRoot;
    }

    public void setOrderRoot(TreeNode<Order> orderRoot) {
        this.orderRoot = orderRoot;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }
}
