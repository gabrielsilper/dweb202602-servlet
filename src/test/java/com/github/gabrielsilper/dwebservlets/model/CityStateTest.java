package com.github.gabrielsilper.dwebservlets.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityStateTest {
    @Test
    public void testCreateCityAndStateClasses() {
        State stateAmazonas = new State();
        stateAmazonas.setName("Amazonas");
        stateAmazonas.setStateCode("AM");

        City city = new City();
        city.setName("Manaus");
        city.setState(stateAmazonas);

        System.out.println(stateAmazonas);
        System.out.println(city);

        assertEquals("Amazonas", stateAmazonas.getName());
        assertEquals("AM", stateAmazonas.getStateCode());

        assertEquals("Manaus", city.getName());
        assertEquals(stateAmazonas, city.getState());
    }
}
