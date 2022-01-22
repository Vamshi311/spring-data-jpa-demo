package com.learning.springdatajpademo.mapper;

import com.learning.springdatajpademo.dto.PersonDto;
import com.learning.springdatajpademo.dto.PostDto;
import com.learning.springdatajpademo.entity.Person;
import com.learning.springdatajpademo.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PostMapper {

    @Mapping(target = "comments", ignore = true)
    Post mapToEntity(PostDto postDto);

    PostDto mapToDto(Post post);

    List<Post> mapToEntity(List<PostDto> postDtos);

    List<PostDto> mapToDto(List<Post> posts);
}
