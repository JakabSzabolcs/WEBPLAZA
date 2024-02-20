package hu.szakdolgozat.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plaza")
public class Plaza extends AbstractCoreEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "plaza", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Shop> shops;

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
}
