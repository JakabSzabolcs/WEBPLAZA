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

    @Override
    public String toString() {
        if (city == null || street == null || houseNumber == null || postalCode == null) {
            return "";
        }
        return postalCode + ", " + city + " " + street + " utca " + houseNumber + ". ";
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
