package com.github.gabrielsilper.dwebservlets.model;

import java.util.Objects;

public class Person {
    private String name;
    private String email;
    private String phone;
    private City city;

    public Person() {
    }

    public Person(String name, String email, String phone, City city) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name)
                && Objects.equals(email, person.email)
                && Objects.equals(phone, person.phone)
                && Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone, city);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
