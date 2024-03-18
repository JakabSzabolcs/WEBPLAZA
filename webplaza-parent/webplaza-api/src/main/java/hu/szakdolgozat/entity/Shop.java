package hu.szakdolgozat.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SHOP")
public class Shop extends AbstractCoreEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
