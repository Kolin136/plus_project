package com.example.spring_plus.post.repository;


import com.example.spring_plus.post.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOrderByUserUsernameAscCreateAtDesc();

  @Modifying
  @Query(value = "delete from Comment c where c.post.id = :postId")
  void deletePostCascadeComments(Long postId);
}