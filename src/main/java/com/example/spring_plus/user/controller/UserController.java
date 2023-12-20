package com.example.spring_plus.user.controller;

import com.example.spring_plus.user.dto.SignupRequestDto;
import com.example.spring_plus.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api")
@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user/signup")
  public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto
  ) {
    userService.signup(requestDto);
    return  ResponseEntity.ok().body("회원가입 완료");
  }
}
