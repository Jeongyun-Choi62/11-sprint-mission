package com.sprint.mission.discodeit.dto.userdto;

import com.sprint.mission.discodeit.entity.UserStatus;

import java.util.UUID;

public record UpdateUserDto(




        UUID userId,
        String newNickname,
        String newEmail,
        String oldPassword,
        String newPassword,
        UUID profileId


        ) {
}
