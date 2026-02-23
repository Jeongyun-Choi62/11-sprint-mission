package com.sprint.mission.discodeit.entity;


public class User extends Entity{

    private String nickname;
    private final String userId;
    private String password;
    private Status status;


    public enum Status {
        ACTIVE, INACTIVE
    }

    public User(String userId, String password, String nickname) {
        super();
        this.password = password;
        this.userId = userId;
        this.nickname = nickname;
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

    public void updateStatus(Status status){
        this.status = status;
        super.updateUpdatedAt();
    }
    public boolean updatePassword(String oldPassword, String newPassword) {


        if(!oldPassword.equals(password)){
            return false;
        }

        this.password = newPassword;
        super.updateUpdatedAt();
        return true;

    }


    public void updateNickname(String nickname) {

        this.nickname = nickname;
        super.updateUpdatedAt();

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
