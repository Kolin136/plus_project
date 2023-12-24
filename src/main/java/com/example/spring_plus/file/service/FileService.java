package com.example.spring_plus.file.service;

import com.example.spring_plus.file.entity.PostFile;
import com.example.spring_plus.file.repository.PostFileRepository;
import com.example.spring_plus.global.exception.CustomException;
import com.example.spring_plus.global.exception.ErrorCode;
import com.example.spring_plus.post.entity.Post;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

  private final PostFileRepository postFileRepository;

  @Value("${file.upload-dir}")
  private String fileUploadDir;

  public List<String> fileSave(List<MultipartFile> files, Post post) {

    List<String> fileUrlList = new ArrayList<>();

    for (MultipartFile file : files) {
      UUID uuid = UUID.randomUUID();
      String fileUrl = fileUploadDir + uuid + "_" + file.getOriginalFilename();

      File pathAsFile = new File(fileUrl);

      try {
        file.transferTo(pathAsFile);
      } catch (IOException e) {
        throw new CustomException(ErrorCode.INTERNAL_ERROR);
      }
      fileUrlList.add("/images/" + uuid + "_" + file.getOriginalFilename());

      PostFile postFile = PostFile.createPostFile(
          "/images/" + uuid + "_" + file.getOriginalFilename(), post);

      postFileRepository.save(postFile);

    }

    return fileUrlList;
  }

}
