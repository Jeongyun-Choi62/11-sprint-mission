package com.sprint.mission.discodeit.dto.userdto;

import com.sprint.mission.discodeit.entity.BinaryContent;

public record CreateUserDto(String nickname,
                            String email,
                            String password,
                            BinaryContent profileImage) {}

