package hu.szakdolgozat.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SHOP")
public class Shop extends Plaza {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PLAZA")
    @ManyToOne
    private Plaza plaza;

    @Column(name = "PRODUCTS")
    @OneToMany
    private List<Product> products;

    @Column(name = "RATING")
    private int rating;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }
}
