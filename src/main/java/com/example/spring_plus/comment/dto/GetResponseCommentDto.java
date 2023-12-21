package com.example.spring_plus.comment.dto;

import com.example.spring_plus.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetResponseCommentDto(
    Long id, String username, String comment, LocalDateTime createAt){

  public static GetResponseCommentDto of(Comment comment){
    return GetResponseCommentDto.builder()
        .id(comment.getId())
        .username(comment.getUser().getUsername())
        .comment(comment.getComment())
        .createAt(comment.getCreateAt())
        .build();
  }
}