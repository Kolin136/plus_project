package com.example.spring_plus.comment.repository;


import com.example.spring_plus.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment,Long> {

  @Modifying
  @Query(value = "delete from Comment c where c.post.id = :postId")
  void deletePostCascadeComments(Long postId);

}
