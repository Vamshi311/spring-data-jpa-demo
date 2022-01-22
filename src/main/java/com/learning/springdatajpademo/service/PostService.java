package com.learning.springdatajpademo.service;

import com.learning.springdatajpademo.dto.PostDto;
import com.learning.springdatajpademo.entity.Comment;
import com.learning.springdatajpademo.entity.Post;
import com.learning.springdatajpademo.mapper.CommentMapper;
import com.learning.springdatajpademo.mapper.PostMapper;
import com.learning.springdatajpademo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postMapper = Mappers.getMapper(PostMapper.class);
        this.commentMapper = Mappers.getMapper(CommentMapper.class);
    }

    public PostDto addPost(PostDto postDto) {
        Post post = postMapper.mapToEntity(postDto);
        List<Comment> comments = commentMapper.mapToEntity(postDto.getComments());
        comments.forEach(c -> {
            c.setPost(post);
        } );

        post.setComments(comments);
        Post savedPost = postRepository.save(post);
        return postMapper.mapToDto(savedPost);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<PostDto> getPosts() {
        return postMapper.mapToDto(postRepository.findAll());
    }
}
