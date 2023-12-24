package com.example.spring_plus.file.repository;

import com.example.spring_plus.file.entity.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileRepository extends JpaRepository<PostFile,Long> {

}
