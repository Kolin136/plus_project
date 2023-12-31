package com.example.spring_plus.user.dto;

import com.example.spring_plus.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.crypto.password.PasswordEncoder;


public record SignupRequestDto(
    @Pattern(regexp = "^[a-z0-9]{3,15}$", message = "이름 양식에 맞게 적어 주세요")
    @NotBlank(message = "이름 공백일 수 없습니다.")
    String username,
    @Pattern(regexp = "^[a-z0-9]{4,15}$", message = "비밀 번호 양식에 맞게 적어 주세요")
    @NotBlank(message = "비밀번호 공백일 수 없습니다.")
    String password,
    @NotBlank(message = "비밀번호 확인 공백일 수 없습니다.")
    String passwordAgain) {

  public User toEntity(PasswordEncoder passwordEncoder) {
    return User.builder()
        .username(username)
        .password(passwordEncoder.encode(password))
        .build();

  }
}
