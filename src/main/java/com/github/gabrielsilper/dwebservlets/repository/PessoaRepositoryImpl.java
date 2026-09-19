package com.github.gabrielsilper.dwebservlets.repository;

import com.github.gabrielsilper.dwebservlets.model.City;
import com.github.gabrielsilper.dwebservlets.model.Person;
import com.github.gabrielsilper.dwebservlets.model.State;

import java.util.List;

public class PessoaRepositoryImpl implements PessoaRepository {
    private final List<Person> personList;

    public PessoaRepositoryImpl() {
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

        this.personList = List.of(person1, person2, person3);
    }

    public PessoaRepositoryImpl(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public List<Person> listPersons() {
        return personList;
    }

    @Override
    public List<Person> listPersons(String filter) {
        return List.of();
    }
}
