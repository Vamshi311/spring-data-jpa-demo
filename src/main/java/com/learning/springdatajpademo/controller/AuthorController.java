package com.learning.springdatajpademo.controller;

import com.learning.springdatajpademo.entity.Author;
import com.learning.springdatajpademo.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public void addAuthors() {
        authorService.addAuthors();
    }

    @GetMapping("/all")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
