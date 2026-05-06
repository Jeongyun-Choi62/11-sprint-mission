package com.sprint.mission.discodeit.exception.service;

import com.sprint.mission.discodeit.exception.AuthException;

public class DiffPasswordException extends AuthException {

  public DiffPasswordException() {
    super("비밀번호가 일치하지 않습니다.");
  }
}
