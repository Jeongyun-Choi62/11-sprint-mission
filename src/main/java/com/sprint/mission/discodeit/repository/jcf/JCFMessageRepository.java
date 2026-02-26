package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCFMessageRepository implements MessageRepository {

    private final Map<String, Message> data ;

    public JCFMessageRepository() {
        data = new HashMap<>();
    }


    @Override
    public boolean saveMessage(Message message) {

        if(data.containsKey(message.getMessageId()))
            return false;

        return data.put(message.getMessageId(),message) != null;

    }

    @Override
    public Message getMessage(String messageId) {
        return data.getOrDefault(messageId,null);
    }

    @Override
    public List<Message> getAllMessage() {
        return data.values().stream().toList();
    }

    @Override
    public boolean updateMessage(Message message) {

        return data.put(message.getMessageId(), message) != null;

    }

    @Override
    public boolean deleteMessage(String messageId) {

        return data.remove(messageId) != null;
    }

    @Override
    public boolean isExistMessage(String messageId) {
        return data.containsKey(messageId);
    }
}
