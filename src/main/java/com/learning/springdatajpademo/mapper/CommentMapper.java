package com.learning.springdatajpademo.mapper;

import com.learning.springdatajpademo.dto.CommentDto;
import com.learning.springdatajpademo.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Mapping(target = "post", ignore = true)
    Comment mapToEntity(CommentDto commentDto);

    CommentDto mapToDto(Comment comment);

    List<Comment> mapToEntity(List<CommentDto> commentDtos);

    List<CommentDto> mapToDto(List<Comment> comments);
}
