package com.learning.springdatajpademo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name= "title")
    private String title;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "author_book", joinColumns = {@JoinColumn(name = "book_id", foreignKey = @ForeignKey(name="fk_book_author_bookId"))},
    inverseJoinColumns = {@JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_book_author_author_id"))})
    private List<Author> authors;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
