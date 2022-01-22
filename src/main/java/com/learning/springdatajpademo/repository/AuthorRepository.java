package com.learning.springdatajpademo.repository;

import com.learning.springdatajpademo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
