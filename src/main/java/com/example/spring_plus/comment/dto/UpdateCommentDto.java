package com.example.spring_plus.comment.dto;

import com.example.spring_plus.comment.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Builder;

public class UpdateCommentDto {

  public record Request(

      @NotBlank(message = "댓글 공백일 수 없습니다.")
      String comment) {

  }

  @Builder
  public record Response(
      Long id, String username, String comment, LocalDateTime modifiedDate) {

    public static UpdateCommentDto.Response of(Comment comment) {
      return UpdateCommentDto.Response.builder()
          .id(comment.getId())
          .username(comment.getUser().getUsername())
          .comment(comment.getComment())
          .modifiedDate(comment.getModifiedDate())
          .build();
    }
  }


}
