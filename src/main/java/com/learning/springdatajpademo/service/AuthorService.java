package com.learning.springdatajpademo.service;

import com.learning.springdatajpademo.entity.Author;
import com.learning.springdatajpademo.entity.Book;
import com.learning.springdatajpademo.entity.Person;
import com.learning.springdatajpademo.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthors() {
        Author jay = Author.builder()
                .name("Jay")
                .books(new ArrayList<>())
                .build();

        Author chris = Author.builder()
                .name("Chris")
                .books(new ArrayList<>())
                .build();

        Author jared = Author.builder()
                .name("Jared")
                .books(new ArrayList<>())
                .build();

        Book adventure = Book.builder()
                .title("Adventure")
                .authors(new ArrayList<>())
                .build();

        Book blackSea = Book.builder()
                .title("Black Sea")
                .authors(new ArrayList<>())
                .build();

        Book galaxy = Book.builder()
                .title("Galaxy")
                .authors(new ArrayList<>())
                .build();

        jay.getBooks().add(adventure);
        adventure.getAuthors().add(jay);
        jay.getBooks().add(blackSea);
        blackSea.getAuthors().add(jay);

        chris.getBooks().add(adventure);
        adventure.getAuthors().add(chris);
        chris.getBooks().add(blackSea);
        blackSea.getAuthors().add(chris);
        chris.getBooks().add(galaxy);
        galaxy.getAuthors().add(chris);

        jared.getBooks().add(adventure);
        adventure.getAuthors().add(jared);
        jared.getBooks().add(blackSea);
        blackSea.getAuthors().add(jared);

        authorRepository.save(jay);
        authorRepository.save(chris);
        authorRepository.save(jared);
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).get();
        author.remove();
        authorRepository.save(author);
        authorRepository.deleteById(id);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

}
