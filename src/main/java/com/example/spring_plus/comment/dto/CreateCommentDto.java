package com.example.spring_plus.comment.dto;

import com.example.spring_plus.comment.entity.Comment;
import com.example.spring_plus.post.entity.Post;
import com.example.spring_plus.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Builder;

public class CreateCommentDto {

  public record Request(

      @NotBlank(message = "댓글 공백일 수 없습니다.")
      String comment) {

    public Comment toEntity(User user,Post post) {
      return Comment.builder()
          .comment(comment)
          .user(user)
          .post(post)
          .build();
    }

  }
  @Builder
  public record Response(
       Long id, String username, String comment, LocalDateTime createAt){

      public static Response of(Comment comment){
        return Response.builder()
            .id(comment.getId())
            .username(comment.getUser().getUsername())
            .comment(comment.getComment())
            .createAt(comment.getCreateAt())
            .build();
      }
  }

}