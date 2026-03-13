package com.sprint.mission.discodeit.service;

public interface MessageService {

    String send(String senderId, String channelId, String message);
    void readMessage(String messageId);
    void readAllMessage();
    void updateMessage(String messageId, String message);
    void deleteMessage(String messageId);




}
