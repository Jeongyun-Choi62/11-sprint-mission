package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;

public interface ChannelRepository {

    void saveChannel(Channel channel);
    User getChannel(Channel channel);
    List<Channel> getAllChannel();
    void updateChannel(Channel channel);
    void deleteChannel(String channelId);
    boolean isExistChannel(String channelId);




}
