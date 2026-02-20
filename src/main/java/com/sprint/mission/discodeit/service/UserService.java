package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

public interface UserService {

    public void createUser(String nickname, String password, String userId);
    public void readUser(String userId);
    public void readAllUser();
    public void updateNickname(String userId, String nickname);
    public void updatePassword(String userId, String oldPassword, String newPassword);
    public void updateStatus(String userId, User.Status status);
    public void deleteUser(String userId, String password);
    public boolean isExistUser(String userId);




}
