package hu.szakdolgozat.entity;

import hu.szakdolgozat.enums.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SHOP")
public class Shop extends AbstractFelhasznalo {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "plaza_id")
    private Plaza plaza;

    public Shop() {
        super.setType(UserType.SHOP_OWNER);
    }

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
}
