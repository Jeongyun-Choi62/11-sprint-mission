package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import lombok.Getter;

@Getter
public class Channel extends BaseUpdatableEntity {

  private String name; //채널 이름
  private final ChannelType type;
  private String description;


  public enum ChannelType {
    PUBLIC,
    PRIVATE
  }

  public Channel(String name, String description, ChannelType type) {
    this.name = name;
    this.description = description;
    this.type = type;
  }

  public void updateName(String name) {
    this.name = name;
  }

  public void updateDescription(String description) {
    this.description = description;
  }


}
