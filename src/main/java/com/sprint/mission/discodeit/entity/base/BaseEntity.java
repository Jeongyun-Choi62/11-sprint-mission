package com.sprint.mission.discodeit.entity.base;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;


@Getter
public abstract class BaseEntity {

  private final UUID id;
  @CreatedDate
  private final Instant createdAt;

  public BaseEntity() {
    this.id = UUID.randomUUID();
    this.createdAt = Instant.now();
  }


}
