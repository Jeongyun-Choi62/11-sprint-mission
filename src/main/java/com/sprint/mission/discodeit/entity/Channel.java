package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Channel extends Entity{

    private String channelName; //채널 이름
    private String ownerId; //채널장
    private final List<String> members; //채널 내 멤버아이디들

    public Channel(String channelName, String ownerId, String channelId) {
        this.channelName = channelName;
        this.ownerId = ownerId;
        members = new ArrayList<>();

        members.add(ownerId);
    }



    public void updateChannelName(String channelName) {
        this.channelName = channelName;
        super.updateUpdatedAt();
    }
    public void addMember(String memberId){
        members.add(memberId);
        super.updateUpdatedAt();
    }
    public int removeMember(String memberId){

        members.remove(memberId);
        if(members.isEmpty()) {

            return 0;

        }
        else if (ownerId.equals(memberId)) {

            String newOwnerId = members.get(0);
            updateOwner(newOwnerId);


        }
        super.updateUpdatedAt();

        return members.size();
    }
    public void updateOwner(String ownerId){
        this.ownerId = ownerId;
        super.updateUpdatedAt();
    }

}
