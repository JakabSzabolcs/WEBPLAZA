package hu.szakdolgozat.entity;


import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String houseNumber;
    private String postalCode;

    public Address() {
    }

    public Address(String city, String street, String houseNumber, String postalCode) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return postalCode;
    }

    public void setZipCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
