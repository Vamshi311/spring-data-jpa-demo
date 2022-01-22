package com.learning.springdatajpademo.controller;

import com.learning.springdatajpademo.dto.PersonDto;
import com.learning.springdatajpademo.entity.Person;
import com.learning.springdatajpademo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
@Slf4j
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public PersonDto addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }

    @GetMapping("/all")
    public List<PersonDto> getPersons() {
        return personService.getPersons();
    }
}
