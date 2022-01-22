package com.learning.springdatajpademo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostDto {

    private Long id;

    @NotBlank(message = "title must not be blank")
    private String title;

    @NotNull(message = "comments must be provided")
    private List<CommentDto> comments;
}
