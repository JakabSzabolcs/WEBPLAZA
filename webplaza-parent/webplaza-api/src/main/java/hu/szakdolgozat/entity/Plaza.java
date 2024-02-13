package hu.szakdolgozat.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PLAZA")
public class Plaza extends CoreEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    @OneToOne
    private Address address;

    @Column(name = "SHOPS")
    @OneToMany
    private List<Shop> shops;

    @Column(name = "COURIERS")
    @OneToMany
    private List<Courier> couriers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }
}
