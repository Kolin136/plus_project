package com.example.spring_plus.post.dto;

import com.example.spring_plus.post.entity.Post;
import com.example.spring_plus.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class CreatePostDto {

  public record Request(
      @NotBlank(message = "제목 공백일 수 없습니다.")
      String title,
      @NotBlank(message = "내용 공백일 수 없습니다.")
      String contents) {

    public Post toEntity(User user) {
      return Post.builder()
          .title(title)
          .contents(contents)
          .user(user)
          .build();
    }

  }
  @Builder
  public record Response(
      Long id, String username, String title, String contents,
      LocalDateTime createAt, List<String> fileUrlList){

      public static Response of(Post post,List<String> fileUrlList){
        return Response.builder()
            .id(post.getId())
            .username(post.getUser().getUsername())
            .title(post.getTitle())
            .contents(post.getContents())
            .createAt(post.getCreateAt())
            .fileUrlList(fileUrlList)
            .build();
      }
  }

}