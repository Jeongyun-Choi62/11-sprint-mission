package com.sprint.mission.discodeit.dto.channeldto;

import com.sprint.mission.discodeit.entity.Channel;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record FindPublicChannelDto(


        UUID channelId,
        UUID ownerId,
        String channelName,
        Channel.ChannelType channelType,
        String channelDescription,
        List<UUID> memberIdList,
        Instant LastMessageTime
) {
}
