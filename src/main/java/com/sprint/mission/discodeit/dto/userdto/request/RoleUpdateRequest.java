package com.sprint.mission.discodeit.dto.userdto.request;

import com.sprint.mission.discodeit.entity.User;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record RoleUpdateRequest(

    @NotBlank
    UUID userID,

    @NotBlank
    User.Role newRole

) {

}
