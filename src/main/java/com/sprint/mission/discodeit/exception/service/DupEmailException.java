package com.sprint.mission.discodeit.exception.service;

import com.sprint.mission.discodeit.exception.UserException;

public class DupEmailException extends UserException {

  public DupEmailException() {
    super("중복되는 이메일 입니다.");
  }
}
