package com.trilogy.cloudgamestoreadmin.model;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Customer {

    private int customerId;
    @NotEmpty(message = "Must include firstName")
    private String firstName;
    @NotEmpty(message = "Must include lastName")
    private String lastName;
    @NotEmpty(message = "Must include street")
    private String street;
    @NotEmpty(message = "must include city")
    private String city;
    @NotEmpty(message = "Must include zip code")
    private String zip;
    @NotEmpty(message = "Must include email")
    private String email;
    @NotEmpty(message = "Must include phone")
    private String phone;


    public Customer(){

    }

    public Customer(int customerId, @NotEmpty(message = "Must include firstName") String firstName, @NotEmpty(message = "Must include lastName") String lastName, @NotEmpty(message = "Must include street") String street, @NotEmpty(message = "must include city") String city, @NotEmpty(message = "Must include zip code") String zip, @NotEmpty(message = "Must include email") String email, @NotEmpty(message = "Must include phone") String phone) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }
    public Customer(@NotEmpty(message = "Must include firstName") String firstName, @NotEmpty(message = "Must include lastName") String lastName, @NotEmpty(message = "Must include street") String street, @NotEmpty(message = "must include city") String city, @NotEmpty(message = "Must include zip code") String zip, @NotEmpty(message = "Must include email") String email, @NotEmpty(message = "Must include phone") String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getCustomerId() == customer.getCustomerId() &&
                getFirstName().equals(customer.getFirstName()) &&
                getLastName().equals(customer.getLastName()) &&
                getStreet().equals(customer.getStreet()) &&
                getCity().equals(customer.getCity()) &&
                getZip().equals(customer.getZip()) &&
                getEmail().equals(customer.getEmail()) &&
                getPhone().equals(customer.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getFirstName(), getLastName(), getStreet(), getCity(), getZip(), getEmail(), getPhone());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
