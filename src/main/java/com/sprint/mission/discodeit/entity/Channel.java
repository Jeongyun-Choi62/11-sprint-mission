package com.sprint.mission.discodeit.entity;

import java.util.ArrayList;
import java.util.List;


public class Channel extends Entity{

    private String channelName; //채널 이름
    private final String channelId; //채널 아이디 (채널이름 + 숫자)
    private String ownerID; //채널장
    private final List<String> members; //채널 내 멤버아이디들

    public Channel(String channelName, String ownerID, String channelId) {
        this.channelName = channelName;
        this.channelId = channelId;
        this.ownerID = ownerID;
        members = new ArrayList<>();
        members.add(ownerID);
    }


    public String getChannelName() {
        return channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getOwner() {
        return ownerID;
    }

    public List<String> getMembers() {
        return members;
    }

    public void updateChannelName(String channelName) {
        this.channelName = channelName;
        super.updateUpdatedtime();
    }
    public void addMember(String memberID){
        members.add(memberID);
    }
    public void removeMember(String memberID){
        members.remove(memberID);
    }
    public void updateOwner(String ownerID){
        this.ownerID = ownerID;
        super.updateUpdatedtime();
    }




    @Override
    public String toString() {
        return "Channel{" +
                "channelName='" + channelName + '\'' +
                ", channelId='" + channelId + '\'' +
                ", ownerID=" + ownerID +
                ", members=" + members +
                '}';
    }
}
