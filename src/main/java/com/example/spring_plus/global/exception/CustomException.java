package com.example.spring_plus.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

  private final int status;

  public CustomException(ErrorCode errorCode){
    super(errorCode.getMessage());
    this.status = errorCode.getStatus();
  }


}
