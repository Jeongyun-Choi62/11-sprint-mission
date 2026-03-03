package com.sprint.mission.discodeit.entity;


import java.util.ArrayList;
import java.util.List;

public class User extends Entity{

    private String nickname;
    private final String userId;
    private String password;
    private Status status;
    private final List<Message>  defaultMessages;


    public enum Status {
        ACTIVE, INACTIVE
    }

    public User(String userId, String password, String nickname) {
        super();
        this.password = password;
        this.userId = userId;
        this.nickname = nickname;
        defaultMessages = new ArrayList<>();
        status = Status.ACTIVE;
    }

    public Status getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }



    public String getNickname() {
        return nickname;
    }

    public String getUserId() {
        return userId;
    }

    public boolean updateStatus(Status status, String password){

        if(!checkSamePassword(password))
            return false;
        this.status = status;
        super.updateUpdatedAt();
        return true;
    }
    public boolean updatePassword(String oldPassword, String newPassword) {


        if(!checkSamePassword(oldPassword))
            return false;

        this.password = newPassword;
        super.updateUpdatedAt();
        return true;

    }


    public boolean updateNickname(String nickname, String password) {

        if(!checkSamePassword(password))
            return false;

        this.nickname = nickname;
        super.updateUpdatedAt();
        return true;


    }

    public List<Message> getDefaultMessages() {
        return defaultMessages;
    }

    public void addDefaultMessage(Message message){
        defaultMessages.add(message);
    }

    public boolean checkSamePassword(String password){

        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", userId='" + userId + '\'' +
                ", status=" + status +

                '}';
    }
}
