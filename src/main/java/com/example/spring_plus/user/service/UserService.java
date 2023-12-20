package com.example.spring_plus.user.service;


import com.example.spring_plus.user.dto.SignupRequestDto;
import com.example.spring_plus.user.entity.User;
import com.example.spring_plus.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void signup(SignupRequestDto requestDto) {

    usernameCheck(requestDto.username());
    passwordCheck(requestDto);
    User user = requestDto.toEntity(passwordEncoder);
    userRepository.save(user);

  }
  public void usernameCheck(String username){
    if (userRepository.findByUsername(username).isPresent()) {

    }
  }

  public void passwordCheck(SignupRequestDto requestDto) {
    if (!(requestDto.password().equals(requestDto.passwordAgain()))) {

    }
    if (requestDto.password().contains(requestDto.username())) {

    }
  }


}
