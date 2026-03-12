package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.userdto.CreateUserDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;

import java.util.UUID;

public interface UserService {

    void createUser(CreateUserDto createUserDTO);
    void readUser(UUID userId);
    void readAllUser();
    void updateUser(UUID userId, String newNickname, String newEmail, UserStatus status, String oldPassword, String newPassword);
    void deleteUser(UUID userId, String password);





}
