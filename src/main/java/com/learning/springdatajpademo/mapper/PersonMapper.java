package com.learning.springdatajpademo.mapper;

import com.learning.springdatajpademo.dto.PersonDto;
import com.learning.springdatajpademo.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Mapping(target = "address", ignore = true)
    Person mapToEntity(PersonDto personDto);

    PersonDto mapToDto(Person person);

    List<Person> mapToEntity(List<PersonDto> personDtos);

    List<PersonDto> mapToDto(List<Person> person);
}
