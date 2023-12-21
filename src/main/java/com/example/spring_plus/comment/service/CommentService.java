package com.example.spring_plus.comment.service;


import com.example.spring_plus.comment.dto.CreateCommentDto;
import com.example.spring_plus.comment.dto.UpdateCommentDto;
import com.example.spring_plus.comment.entity.Comment;
import com.example.spring_plus.comment.repository.CommentRepository;
import com.example.spring_plus.global.exception.CustomException;
import com.example.spring_plus.global.exception.ErrorCode;
import com.example.spring_plus.post.entity.Post;
import com.example.spring_plus.post.repository.PostRepository;
import com.example.spring_plus.user.entity.User;
import com.example.spring_plus.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final PostRepository postRepository;
  private final UserRepository userRepository;


  @Transactional
  public CreateCommentDto.Response createComment(Long postId, CreateCommentDto.Request requestDto,
      Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() ->
        new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION)
    );

    Post post = findPost(postId);
    Comment comment = requestDto.toEntity(user,post);
    commentRepository.save(comment);

    return CreateCommentDto.Response.of(comment);
  }

  @Transactional
  public UpdateCommentDto.Response updateComment(Long commentId, UpdateCommentDto.Request requestDto,
      User user) {
    Comment comment = findComment(commentId);

    if (!comment.getUser().getUsername().equals(user.getUsername())) {
      throw new CustomException(ErrorCode.NOT_USER_OWNED_POST_EXCEPTION);
    }

    comment.updateComment(requestDto.comment());

    return UpdateCommentDto.Response.of(comment);
  }

  @Transactional
  public void deleteComment(Long commentId, User user) {
    Comment comment = findComment(commentId);

    if (!comment.getUser().getUsername().equals(user.getUsername())) {
      throw new CustomException(ErrorCode.NOT_USER_OWNED_POST_EXCEPTION);
    }

    commentRepository.delete(comment);

  }

  private Comment findComment(Long id) {
    return commentRepository.findById(id).orElseThrow(() ->
        new CustomException(ErrorCode.NOT_FOUND_COMMENT_EXCEPTION)
    );
  }

  private Post findPost(Long id) {
    return postRepository.findById(id).orElseThrow(() ->
        new CustomException(ErrorCode.NOT_FOUND_POST_EXCEPTION)
    );
  }


}
