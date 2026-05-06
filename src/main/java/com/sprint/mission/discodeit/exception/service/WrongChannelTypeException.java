package com.sprint.mission.discodeit.exception.service;

import com.sprint.mission.discodeit.exception.ChannelException;

public class WrongChannelTypeException extends ChannelException {

  public WrongChannelTypeException(String message) {
    super(message);
  }
}
