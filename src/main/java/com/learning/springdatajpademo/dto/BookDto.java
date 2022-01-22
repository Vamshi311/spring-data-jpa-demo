package com.learning.springdatajpademo.dto;

import com.learning.springdatajpademo.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookDto {

    private Long id;

    private String title;

    //private List<AuthorDto> authors;
}
