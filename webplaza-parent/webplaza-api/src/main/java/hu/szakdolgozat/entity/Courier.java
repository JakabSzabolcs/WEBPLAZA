package hu.szakdolgozat.entity;

import hu.szakdolgozat.enums.OrderState;

import javax.persistence.*;

@Entity
@Table(name = "courier")
public class Courier extends CoreEntity {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "courier")
    private Order currentOrder;

    @ManyToOne
    @JoinColumn(name = "plaza_id")
    private Plaza plaza;

    @Column(name = "order_state", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'WAITING'")
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
