package com.learning.springdatajpademo.service;

import com.learning.springdatajpademo.dto.AddressDto;
import com.learning.springdatajpademo.dto.PersonDto;
import com.learning.springdatajpademo.entity.Address;
import com.learning.springdatajpademo.entity.Person;
import com.learning.springdatajpademo.entity.Post;
import com.learning.springdatajpademo.mapper.AddressMapper;
import com.learning.springdatajpademo.mapper.PersonMapper;
import com.learning.springdatajpademo.repository.PersonRepository;
import com.learning.springdatajpademo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final AddressMapper addressMapper;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.personMapper = Mappers.getMapper(PersonMapper.class);
        this.addressMapper = Mappers.getMapper(AddressMapper.class);
    }

    public PersonDto addPerson(PersonDto personDto) {
        Person person = personMapper.mapToEntity(personDto);
        AddressDto addressDto = personDto.getAddress();
        Address address = addressMapper.mapToEntity(addressDto);

        address.setPerson(person);
        person.setAddress(address);
        Person savedPerson = personRepository.save(person);
        return personMapper.mapToDto(savedPerson);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public List<PersonDto> getPersons() {
        return personMapper.mapToDto(personRepository.findAll());
    }

}
