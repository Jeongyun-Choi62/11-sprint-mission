package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.userdto.CreateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UserInfoDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserInfoDto createUser(CreateUserDto createUserDTO);
    UserInfoDto readUser(UUID userId);
    List<UserInfoDto> readAllUser();
    UserInfoDto updateUser(UUID userId, String newNickname, String newEmail, UserStatus status, String oldPassword, String newPassword);
    boolean deleteUser(UUID userId, String password);





}
