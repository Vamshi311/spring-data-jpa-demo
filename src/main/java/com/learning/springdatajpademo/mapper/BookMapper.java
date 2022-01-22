package com.learning.springdatajpademo.mapper;

import com.learning.springdatajpademo.dto.BookDto;
import com.learning.springdatajpademo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface BookMapper {

    @Mapping(target = "authors", ignore = true)
    Book mapToEntity(BookDto bookDto);

    BookDto mapToDto(Book book);

    List<Book> mapToEntity(List<BookDto> bookDtos);

    List<BookDto> mapToDto(List<Book> books);
}
