package com.example.spring_plus.post.dto;

import com.example.spring_plus.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Builder;

public class UpdatePostDto {

  public record Request(
      @NotBlank(message = "제목 공백일 수 없습니다.")
      String title,
      @NotBlank(message = "내용 공백일 수 없습니다.")
      String contents) {

  }

  @Builder
  public record Response(
      Long id, String username, String title, String contents, LocalDateTime modifiedDate) {

    public static UpdatePostDto.Response of(Post post) {
      return UpdatePostDto.Response.builder()
          .id(post.getId())
          .username(post.getUser().getUsername())
          .title(post.getTitle())
          .contents(post.getContents())
          .modifiedDate(post.getModifiedDate())
          .build();
    }
  }


}
