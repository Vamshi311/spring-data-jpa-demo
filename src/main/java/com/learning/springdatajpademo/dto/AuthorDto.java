package com.learning.springdatajpademo.dto;

import com.learning.springdatajpademo.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthorDto {

    private Long id;

    private String name;

    private List<BookDto> books;
}
