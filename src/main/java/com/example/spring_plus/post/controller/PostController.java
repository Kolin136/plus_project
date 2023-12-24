package com.example.spring_plus.post.controller;


import com.example.spring_plus.global.security.userdetails.UserDetailsImpl;
import com.example.spring_plus.post.dto.CreatePostDto;
import com.example.spring_plus.post.dto.GetResponsePostDto;
import com.example.spring_plus.post.dto.UpdatePostDto;
import com.example.spring_plus.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;


  @PostMapping()
  public ResponseEntity<CreatePostDto.Response> createPost(
      @RequestPart(value ="dto") CreatePostDto.Request requestDto,
      @RequestPart(value = "files")  List<MultipartFile> files,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    CreatePostDto.Response responseDto = postService.createPost(requestDto,files,
        userDetails.getUser().getId());

    return ResponseEntity.ok(responseDto);
  }


  @GetMapping("/{id}")
  public ResponseEntity<GetResponsePostDto> getIdPost(@PathVariable Long id) {

    GetResponsePostDto responsePostDto = postService.getIdCard(id);

    return ResponseEntity.ok(responsePostDto);
  }

  @GetMapping("/paging")
  public ResponseEntity<Page<GetResponsePostDto>> getPostPage(
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      @RequestParam("sortBy") String sortBy,
      @RequestParam("isAsc") boolean isAsc) {

    Page<GetResponsePostDto> responsePostDtoPage = postService.getPostPage(page-1,size,sortBy,isAsc);

    return ResponseEntity.ok(responsePostDtoPage);
  }

  @GetMapping()
  public ResponseEntity<List<GetResponsePostDto>> getAllPost() {

    List<GetResponsePostDto> responsePostDtoList = postService.getAllPost();

    return ResponseEntity.ok(responsePostDtoList);
  }


  @PutMapping("/{id}")
  public ResponseEntity<UpdatePostDto.Response> updatePost(@PathVariable Long id,
      @RequestBody UpdatePostDto.Request requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    UpdatePostDto.Response selectCardResponseDto = postService.updatePost(id, requestDto,
        userDetails.getUser());

    return ResponseEntity.ok(selectCardResponseDto);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    postService.deletePost(id, userDetails.getUser());

    return ResponseEntity.ok().body(String.format("%d번 글 삭제 완료", id));
  }




}
