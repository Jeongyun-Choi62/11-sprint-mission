package com.sprint.mission.discodeit.dto.channeldto;

import com.sprint.mission.discodeit.entity.Channel;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record FindPrivateChannelDto(

        UUID channelId,
        UUID ownerId,
        Channel.ChannelType channelType,
        List<UUID> memberIdList,
        Instant LastMessageTime
) {
}
