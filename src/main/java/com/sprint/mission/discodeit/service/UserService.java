package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.userdto.CreateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UpdateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UserInfoDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserInfoDto create(CreateUserDto createUserDTO);
    UserInfoDto find(UUID userId);
    List<UserInfoDto> findAll();
    UserInfoDto updateUser(UpdateUserDto updateUserDto);
    boolean delete(UUID userId, String password);







}
