package com.learning.springdatajpademo.repository;

import com.learning.springdatajpademo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
