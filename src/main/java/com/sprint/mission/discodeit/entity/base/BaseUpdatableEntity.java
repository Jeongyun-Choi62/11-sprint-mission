package com.sprint.mission.discodeit.entity.base;

import lombok.Getter;

import java.time.Instant;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
public abstract class BaseUpdatableEntity extends BaseEntity {

  @LastModifiedDate
  private Instant updatedAt;


  public BaseUpdatableEntity() {

  }


}
