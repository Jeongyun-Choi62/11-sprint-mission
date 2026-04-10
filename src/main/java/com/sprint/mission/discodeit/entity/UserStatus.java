package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class UserStatus extends BaseUpdatableEntity {

  private final User user;
  private Instant lastActiveAt;

  public UserStatus(User user, Instant lastActiveAt) {
    this.user = user;
    this.lastActiveAt = lastActiveAt;
  }

  public void updateLastActiveAt(Instant lastActiveAt) {
    this.lastActiveAt = lastActiveAt;
  }

  public boolean isOnline() {

    Instant now = Instant.now();

    if (getLastActiveAt().isAfter(now.minusSeconds(300))) {

      return true;
    } else {
      return false;
    }


  }


}
