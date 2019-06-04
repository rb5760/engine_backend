package com.search.engine.rest;

import com.search.engine.model.Person;
import com.search.engine.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Api( description="API pour les opérations CRUD d'une personne.")
public class PersonController {

    @Autowired
    PersonService managementService;

    @PostMapping
    @ApiOperation(value = "Ajoute une personne en base")
    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    @CrossOrigin
    public Person createPerson(@RequestBody Person person) {

        final Person insertPerson = managementService.insertPerson(person);

        if (insertPerson != null) {
            return insertPerson;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error saving new person");
        }
    }

    @PostMapping
    @ApiOperation(value = "Update une personne en base")
    @RequestMapping(value = "/persons/update/{id}", method = RequestMethod.POST)
    @CrossOrigin
    public Person updatePerson(@RequestBody Person person, @PathVariable("id") String id) {

        Long id_ = Long.valueOf(id);
        final Person updatePerson = managementService.updatePerson(person, id_);

        if (updatePerson != null) {
            return updatePerson;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating new person");
        }
    }

    @DeleteMapping
    @ApiOperation(value = "Supprime une personne en base")
    @RequestMapping(value = "/persons/delete/{id}", method = RequestMethod.DELETE)
    @CrossOrigin
    public boolean deletePerson(@PathVariable("id") Long id) {

        return managementService.deletePerson(id);

    }

    @GetMapping
    @ApiOperation(value = "Récupérer toutes les personnes en base")
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    @CrossOrigin
    public List<Person> getAllPersons() {
        return managementService.getAllPersons();
    }

    @GetMapping
    @ApiOperation(value = "Récupérer une personne en base")
    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    @CrossOrigin
    public Person getPerson(@PathVariable("id") Long id) {
        return managementService.getPerson(id);
    }



}
