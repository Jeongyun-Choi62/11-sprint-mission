package com.sprint.mission.discodeit.dto.binarycontentdto;

import com.sprint.mission.discodeit.entity.BinaryContent;

import java.util.UUID;

public record BinaryContentInfoDto (
        UUID binaryContentId,
        UUID userId,
        BinaryContent.Type type)
{}
