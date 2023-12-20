package com.example.spring_plus.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  // 공통
  INVALID_INPUT_EXCEPTION(400,"잘못된 입력 입니다!, 다시 입력해주세요"),
  DAILY_REQUEST_LIMIT_EXCEEDED(429,"일일 사용 횟수 초과 했습니다"),

  // 게시글
  NOT_FOUND_POST_EXCEPTION(400,"해당 글은 없습니다"),
  NOT_USER_OWNED_POST_EXCEPTION(403,"해당글은 해당 유저의 글이 아닙니다."),

  // 댓글
  NOT_FOUND_COMMENT_EXCEPTION(400,"해당 댓글은 없습니다"),

  // 회원
  ALREADY_EXIST_USER_NAME_EXCEPTION(409, "이미 존재하는 이름입니다."),
  WRONG_PASSWORD_EXCEPTION(400, "틀린 비밀번호입니다."),
  FAILED_AUTHENTICATION_EXCEPTION(401, "인증에 실패하였습니다."),
  INVALID_PASSWORD_EXCEPTION(400,"비밀번호에 이름이 포함되면 안됩니다."),
  NOT_FOUND_USER_EXCEPTION(400,"해당 유저는 없습니다.");


  private final int status;

  private  final String message;

  ErrorCode(int status,String message){
    this.status = status;
    this.message = message;
  }

}
