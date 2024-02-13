package hu.szakdolgozat.entity;


import hu.szakdolgozat.enums.Currency;
import hu.szakdolgozat.enums.ProductCategory;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product extends CoreEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "CURRENCY")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
