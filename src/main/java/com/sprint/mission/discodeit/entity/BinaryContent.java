package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class BinaryContent extends Entity{

    UUID userID;
    UUID MessageId;
    Type type;
    //String url;



    public enum Type{
        PROFILEIMG,
        IMAGE,
        VIDEO,
        AUDIO,
        FILE
    }

    public BinaryContent(UUID userID, UUID messageId, Type type) {
        this.userID = userID;
        this.type = type;
        MessageId = messageId;

    }

    public BinaryContent(UUID userID) {
        this.userID = userID;
        this.type = Type.PROFILEIMG;
        MessageId = null;
    }



    public void updateUpdatedAt(){



    }

    @Override
    public String toString() {
        return "BinaryContent{" +
                "userID=" + userID +
                ", MessageId=" + MessageId +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
