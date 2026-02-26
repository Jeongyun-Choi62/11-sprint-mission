package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;

public interface UserRepository {

    void saveUser(User user);
    User getUser(String userId);
    List<User> getAllUser();
    void updateUser(User user);
    void deleteUser(String userId);
    boolean isExistUser(String userId);







}
