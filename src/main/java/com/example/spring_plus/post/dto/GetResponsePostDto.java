package com.example.spring_plus.post.dto;

import com.example.spring_plus.comment.dto.GetResponseCommentDto;
import com.example.spring_plus.post.entity.Post;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record GetResponsePostDto(
    Long id, String title, String contents, String username, LocalDateTime createAt,
    Set<GetResponseCommentDto> commentDtoSet) {

  public static GetResponsePostDto of(Post post){
    return GetResponsePostDto.builder()
        .id(post.getId())
        .username(post.getUser().getUsername())
        .title(post.getTitle())
        .contents(post.getContents())
        .createAt(post.getCreateAt())
        .commentDtoSet(post.getCommentSet().stream().map(GetResponseCommentDto::of).collect(Collectors.toSet()))
        .build();
  }

}
