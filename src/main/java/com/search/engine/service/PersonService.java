package com.search.engine.service;

import com.search.engine.dao.PersonRepository;
import com.search.engine.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person insertPerson(final Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Person person, final Long id) {
        final Optional<Person> id1 = personRepository.findById(id);

        if (!id1.isPresent()) {
            return null;
        }

        person.setId(id);
//        return null;
        return personRepository.save(person);
    }

    public boolean deletePerson(final Long id) {
        final Optional<Person> id1 = personRepository.findById(id);

        if (!id1.isPresent()) {
            return false;
        }

        personRepository.delete(id1.get());

        return true;

    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPerson(final Long id) {
        final Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()) return null;
        return person.get();
    }




}
