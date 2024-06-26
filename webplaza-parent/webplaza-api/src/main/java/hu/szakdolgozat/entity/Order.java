package hu.szakdolgozat.entity;

import hu.szakdolgozat.enums.OrderState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends AbstractCoreEntity {

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @OneToOne
    @JoinColumn(name = "courier_id")
    private User courier;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> products;


    @Embedded
    private Address deliveryAddress;

    @Column(name = "order_state", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'NEW'")
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public Order() {
        this.orderState = OrderState.NEW;
        this.products = List.of();
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public User getCourier() {
        return courier;
    }

    public void setCourier(User courier) {
        this.courier = courier;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}
