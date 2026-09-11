package com.github.gabrielsilper.dwebservlets.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    public void testCreateClassPerson() {
        Person person = new Person();

        String name = "Gabriel";
        String email = "gabriel@test.com";
        String phone = "92 99999-9999";

        person.setName(name);
        person.setEmail(email);
        person.setPhone(phone);

        System.out.println(person);

        assertEquals(name, person.getName());
        assertEquals(email, person.getEmail());
        assertEquals(phone, person.getPhone());
    }

    @Test
    public void testCreatePersonWithCity() {
        State stateAmazonas = new State();
        stateAmazonas.setName("Amazonas");
        stateAmazonas.setStateCode("AM");

        City city = new City();
        city.setName("Manaus");
        city.setState(stateAmazonas);

        Person person = new Person();
        person.setName("Gabriel");
        person.setEmail("gabriel@test.com");
        person.setPhone("92 99999-9999");
        person.setCity(city);

        System.out.println(person);

        assertEquals(city.getName(), person.getCity().getName());
        assertEquals(city.getState().getName(), person.getCity().getState().getName());
    }
}
