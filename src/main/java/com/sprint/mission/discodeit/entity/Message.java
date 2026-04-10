package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import java.util.ArrayList;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Message extends BaseUpdatableEntity {

  private String content;
  private final User author; //전송자 아이디
  private final Channel channel; //채널 아이디
  private List<BinaryContent> attachments = new ArrayList<>();


  public Message(User author, Channel channel, String content, List<BinaryContent> attachments) {
    this.author = author;
    this.channel = channel;
    this.content = content;
    this.attachments = attachments;
  }

  public void updateContent(String content) {
    this.content = content;
  }

  public void updateAttachments(List<BinaryContent> attachments) {
    this.attachments = attachments;
  }

}
