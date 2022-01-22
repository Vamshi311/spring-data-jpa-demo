package com.learning.springdatajpademo.repository;

import com.learning.springdatajpademo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
