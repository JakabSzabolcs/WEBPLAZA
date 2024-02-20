package hu.szakdolgozat.entity;

import hu.szakdolgozat.enums.OrderState;
import hu.szakdolgozat.enums.UserType;

import javax.persistence.*;

@Entity
@Table(name = "courier")
public class Courier extends AbstractFelhasznalo {

    @OneToOne(mappedBy = "courier")
    private Order currentOrder;

    public Courier() {
        //default beállításként egy futár futár lesz
        super.setType(UserType.COURIER);
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

}
