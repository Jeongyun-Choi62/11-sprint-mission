package com.sprint.mission.discodeit.entity;

public class Message extends Entity {

    private String message;
    private final String messageID;
    private final String senderId;
    private final String channelId;

    public Message(String senderId, String channelId, String message) {

        super();
        messageID = "msg-" + super.getId();
        this.senderId = senderId;
        this.channelId = channelId;
        this.message = message;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessage() {
        return message;
    }

    public String getsenderId() {
        return senderId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void UpdateMessage(String message){
        this.message = message;
    }


    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", messageID='" + messageID + '\'' +
                ", senderId='" + senderId + '\'' +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
