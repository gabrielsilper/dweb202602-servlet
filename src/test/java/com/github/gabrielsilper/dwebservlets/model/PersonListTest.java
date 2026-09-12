package com.github.gabrielsilper.dwebservlets.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PersonListTest {

    @Test
    public void testCreatePersonsList() {
        State stateAmazonas = new State();
        stateAmazonas.setName("Amazonas");
        stateAmazonas.setStateCode("AM");

        City cityManaus = new City();
        cityManaus.setName("Manaus");
        cityManaus.setState(stateAmazonas);

        Person person1 = new Person(
                "Gabriel",
                "gabriel@test.com",
                "92 99999-9999",
                cityManaus
        );

        Person person2 = new Person(
                "Fulano",
                "fulano@test.com",
                "92 99888-8888",
                cityManaus
        );

        Person person3 = new Person(
                "Beltrana",
                "beltrana@test.com",
                "92 99777-7777",
                cityManaus
        );

        List<Person> persons = List.of(person1, person2, person3);

        for (Person person : persons) {
            System.out.println(person);
        }

        Assertions.assertThat(persons).isNotEmpty();
        Assertions.assertThat(persons.size()).isEqualTo(3);
        Assertions.assertThat(persons.get(0)).isInstanceOf(Person.class);
    }
}
