package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.userdto.CreateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UpdateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UserInfoDto;
import com.sprint.mission.discodeit.entity.UserStatus;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserInfoDto createUser(CreateUserDto createUserDTO);
    UserInfoDto readUser(UUID userId);
    List<UserInfoDto> readAllUser();
    UserInfoDto updateUser(UpdateUserDto updateUserDto);
    boolean deleteUser(UUID userId, String password);





}
