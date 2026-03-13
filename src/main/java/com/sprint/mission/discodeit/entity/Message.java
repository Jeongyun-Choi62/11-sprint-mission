package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Message extends Entity {

    private String message;
    private final UUID senderId; //전송자 아이디
    private final UUID channelId; //채널 아이디





    public Message(UUID senderId, UUID channelId, String message) {

        super();
        this.senderId = senderId;
        this.channelId = channelId;
        this.message = message;

    }



    public void updateMessage(String message){

        this.message = message;
        super.updateUpdatedAt();
    }





    @Override

    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", senderId='" + senderId + '\'' +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
