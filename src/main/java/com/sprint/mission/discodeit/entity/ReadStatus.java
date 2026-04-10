package com.sprint.mission.discodeit.entity;


import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import java.time.Instant;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ReadStatus extends BaseUpdatableEntity {

  private final Channel channel;
  private final User user;
  private Instant lastReadAt;

  public ReadStatus(Channel channel, User user, Instant lastReadAt) {
    this.channel = channel;
    this.user = user;
    this.lastReadAt = lastReadAt;
  }

  public Instant updateLastReadAt(Instant lastReadAt) {
    this.lastReadAt = lastReadAt;
    return lastReadAt;
  }


}
