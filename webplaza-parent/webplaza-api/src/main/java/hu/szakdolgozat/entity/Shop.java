package hu.szakdolgozat.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SHOP")
public class Shop extends CoreEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "plaza_id")
    private Plaza plaza;

    @OneToMany(mappedBy = "shop")
    private List<Product> products;

    @Column(name = "rating")
    private int rating;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
