package com.github.gabrielsilper.dwebservlets.repository;

import com.github.gabrielsilper.dwebservlets.model.Person;

import java.util.List;

public interface PessoaRepository {
    List<Person> listPersons();
    List<Person> listPersons(String filter);
}
