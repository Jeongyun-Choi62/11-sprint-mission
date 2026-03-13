package com.sprint.mission.discodeit.dto.channeldto;

import java.util.UUID;

public record UpdateChannelDto(

        UUID channelId,
        String ChannelName,
        UUID ownerId,
        String ChannelDescription
) {
}
