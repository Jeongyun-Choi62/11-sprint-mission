package com.sprint.mission.discodeit.entity;


import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class User extends Entity{

    private String nickname; //닉네임
    private String email;
    @Getter(AccessLevel.NONE)
    private String password; //비밀번호
    private BinaryContent profileImage;



    public enum Status {
        ACTIVE, INACTIVE
    }

    public User(String nickname, String email, String password, BinaryContent profileImage) {
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
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
    public boolean updateEmail(String email, String password) {

        if(!checkSamePassword(password))
            return false;

        this.email = email;
        super.updateUpdatedAt();
        return true;
    }

    public boolean updateProfileImage(BinaryContent profileImage, String password){
        if(!checkSamePassword(password))
            return false;

        this.profileImage = profileImage;
        super.updateUpdatedAt();
        return true;
    }

    public boolean checkSamePassword(String password) {
        return this.password.equals(password);
    }



    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                 '}';
    }
}
