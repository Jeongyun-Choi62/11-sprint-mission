package com.sprint.mission.discodeit.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {


  USER_NOT_FOUND,
  DUPLICATE_USER,
  CHANNEL_NOT_FOUND,
  PRIVATE_CHANNEL_UPDATE;


  private String message;


}
