package com.learning.springdatajpademo.repository;

import com.learning.springdatajpademo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
