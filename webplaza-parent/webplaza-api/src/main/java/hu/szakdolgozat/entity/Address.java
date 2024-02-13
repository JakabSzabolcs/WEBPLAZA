package hu.szakdolgozat.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

    @Entity
    @Table(name = "ADDRESS")
    public class Address extends CoreEntity {
        @Column(name = "CITY")
        private String city;
        @Column(name = "STREET")
        private String street;
        @Column(name = "HOUSE_NUMBER")
        private String houseNumber;
        @Column(name = "ZIP_CODE")
        private String zipCode;

    public Address() {
    }

    public Address(String city, String street, String houseNumber, String zipCode) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
