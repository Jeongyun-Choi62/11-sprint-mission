package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    boolean saveUser(User user);
    User getUser(UUID userId);
    List<User> getAllUser();
    boolean updateUser(User user);
    boolean deleteUser(UUID userId);
    boolean isExistUser(UUID userId);







}
