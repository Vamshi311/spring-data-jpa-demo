package com.learning.springdatajpademo.controller;

import com.learning.springdatajpademo.dto.PostDto;
import com.learning.springdatajpademo.entity.Post;
import com.learning.springdatajpademo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDto addPost(@RequestBody PostDto postDto) {
        return postService.addPost(postDto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/all")
    public List<PostDto> getPosts() {
        return postService.getPosts();
    }
}
