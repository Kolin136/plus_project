package com.example.spring_plus.file.entity;

import com.example.spring_plus.global.auditing.Timestamped;
import com.example.spring_plus.post.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
public class PostFile extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  //원본 이미지 이름+UUID
  @Column(nullable = false, unique = true)
  private String url;


  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  public static PostFile createPostFile(String url, Post post) {
    return PostFile.builder()
        .url(url)
        .post(post)
        .build();
  }
}
