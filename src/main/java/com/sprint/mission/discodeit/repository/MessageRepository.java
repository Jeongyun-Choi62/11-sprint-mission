package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {

    boolean saveMessage(Message message);
    Message getMessage(UUID messageId);
    List<Message> getAllMessage();
    boolean updateMessage(Message message);
    boolean deleteMessage(UUID messageId);
    boolean isExistMessage(UUID messageId);
    boolean channelsMessagedelete(UUID channelId);


}
