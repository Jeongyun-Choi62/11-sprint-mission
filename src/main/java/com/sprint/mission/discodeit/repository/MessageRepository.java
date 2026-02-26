package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;

public interface MessageRepository {

    void saveMessage(Message message);
    Message getMessage(String messageId);
    List<Message> getAllMessage();
    void updateMessage(Message message);
    void deleteMessage(String messageId);
    boolean isExistMessage(String messageId);


}
