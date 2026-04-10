package com.sprint.mission.discodeit.entity;


import com.sprint.mission.discodeit.entity.base.BaseEntity;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
public class BinaryContent extends BaseEntity {

  private final String fileName;
  private final String contentType;
  private final Long size;
  private final byte[] bytes;


  public BinaryContent(String fileName, String contentType, byte[] bytes, Long size) {
    this.fileName = fileName;
    this.contentType = contentType;
    this.bytes = bytes;
    this.size = size;
  }

  
}

