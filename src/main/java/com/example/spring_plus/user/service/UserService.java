package com.example.spring_plus.user.service;


import com.example.spring_plus.global.exception.CustomException;
import com.example.spring_plus.global.exception.ErrorCode;
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
      throw new CustomException(ErrorCode.ALREADY_EXIST_USER_NAME_EXCEPTION);
    }
  }

  public void passwordCheck(SignupRequestDto requestDto) {
    if (!(requestDto.password().equals(requestDto.passwordAgain()))) {
      throw new CustomException(ErrorCode.WRONG_PASSWORD_EXCEPTION);
    }
    if (requestDto.password().contains(requestDto.username())) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD_EXCEPTION);
    }
  }


}
