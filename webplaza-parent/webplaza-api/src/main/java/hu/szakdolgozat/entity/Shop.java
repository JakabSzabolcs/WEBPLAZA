package hu.szakdolgozat.entity;

import hu.szakdolgozat.enums.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SHOP")
public class Shop extends AbstractCoreEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "plaza_id")
    private Plaza plaza;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
