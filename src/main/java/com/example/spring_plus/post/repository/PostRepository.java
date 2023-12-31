package com.example.spring_plus.post.repository;


import com.example.spring_plus.post.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOrderByUserUsernameAscCreateAtDesc();

}