package com.example.spring_plus.user.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
    @NotBlank(message = "이름 공백일 수 없습니다.")
    String username,
    @NotBlank(message = "비밀번호 공백일 수 없습니다.")
    String password
) {
}
