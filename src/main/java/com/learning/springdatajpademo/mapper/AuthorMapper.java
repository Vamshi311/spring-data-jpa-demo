package com.learning.springdatajpademo.mapper;

import com.learning.springdatajpademo.dto.AuthorDto;
import com.learning.springdatajpademo.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AuthorMapper {

    @Mapping(target = "books", ignore = true)
    Author mapToEntity(AuthorDto authorDto);

    AuthorDto mapToDto(Author author);

    List<Author> mapToEntity(List<AuthorDto> authorDtos);

    List<AuthorDto> mapToDto(List<Author> authors);
}
