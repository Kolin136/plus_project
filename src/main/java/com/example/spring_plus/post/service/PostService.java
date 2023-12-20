package com.example.spring_plus.post.service;


import com.example.spring_plus.global.exception.CustomException;
import com.example.spring_plus.global.exception.ErrorCode;
import com.example.spring_plus.post.dto.CreatePostDto;
import com.example.spring_plus.post.dto.CreatePostDto.Response;
import com.example.spring_plus.post.dto.GetResponsePostDto;
import com.example.spring_plus.post.dto.UpdatePostDto;
import com.example.spring_plus.post.entity.Post;
import com.example.spring_plus.post.repository.PostRepository;
import com.example.spring_plus.user.entity.User;
import com.example.spring_plus.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CreatePostDto.Response createPost(CreatePostDto.Request requestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
            new CustomException(ErrorCode.NOT_FOUND_USER_EXCEPTION)
        );
        Post post = requestDto.toEntity(user);
        postRepository.save(post);

        return Response.of(post);
    }

    @Transactional(readOnly = true)
    public GetResponsePostDto getIdCard(Long id) {
        Post post = findPost(id);

        return GetResponsePostDto.of(post);
    }
    @Transactional(readOnly = true)
    public List<GetResponsePostDto> getAllPost() {
        List<Post> postList=  postRepository.findAllByOrderByUserUsernameAscCreateAtDesc();
        return postList.stream().map(GetResponsePostDto::of).toList();
    }

    @Transactional
    public UpdatePostDto.Response updatePost(Long id, UpdatePostDto.Request requestDto, User user) {
        Post post = findPost(id);

        if (!post.getUser().getUsername().equals(user.getUsername())){
            throw new CustomException(ErrorCode.NOT_USER_OWNED_POST_EXCEPTION);
        }

        post.updatePost(requestDto.title(),requestDto.contents());

        return UpdatePostDto.Response.of(post);
    }

    @Transactional
    public void deletePost(Long id, User user) {
        Post post = findPost(id);

        if (!post.getUser().getUsername().equals(user.getUsername())){
            throw new CustomException(ErrorCode.NOT_USER_OWNED_POST_EXCEPTION);
        }

        postRepository.delete(post);

    }



    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
             new CustomException(ErrorCode.NOT_FOUND_POST_EXCEPTION)
        );
    }


}