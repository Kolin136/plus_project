package com.example.spring_plus.comment.controller;


import com.example.spring_plus.comment.dto.CreateCommentDto;
import com.example.spring_plus.comment.dto.UpdateCommentDto;
import com.example.spring_plus.comment.service.CommentService;
import com.example.spring_plus.global.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/{postId}")
  public ResponseEntity<CreateCommentDto.Response> createComment(@PathVariable Long postId,
      @RequestBody CreateCommentDto.Request requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    CreateCommentDto.Response commentResponseDto = commentService.createComment(postId, requestDto,
        userDetails.getUser().getId());
    return ResponseEntity.ok(commentResponseDto);

  }

  @PutMapping("/{commentId}")
  public ResponseEntity<UpdateCommentDto.Response> updateComment(
      @PathVariable Long commentId,
      @RequestBody UpdateCommentDto.Request requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    UpdateCommentDto.Response commentResponseDto = commentService.updateComment(commentId,
        requestDto, userDetails.getUser());
    return ResponseEntity.ok(commentResponseDto);

  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<String> deleteCard(@PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    commentService.deleteComment(commentId, userDetails.getUser());
    return ResponseEntity.ok().body(String.format("%d번 댓글 삭제 완료", commentId));

  }

}
