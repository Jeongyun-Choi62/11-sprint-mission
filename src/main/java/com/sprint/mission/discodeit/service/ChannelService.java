package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.channeldto.*;
import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChannelService {

    boolean createPrivate(CreatePrivateChannelDto createPrivateChannelDto);
    boolean createPublic(CreatePublicChannelDto createPublicChannelDto);

    Optional<FindPublicChannelDto> findPrivate(UUID channelId, UUID memberId);
    Optional<FindPrivateChannelDto> findPublic(UUID channelId);


    List<FindPrivateChannelDto> findAllPrivateById(UUID userId);
    List<FindPublicChannelDto> findAllPublicById(UUID userId);



    UpdateChannelDto updateChannel(UpdateChannelDto updateChannelDto);


    void addMember(ChannelMemberDto channelMemberDto);
    void removeMember(ChannelMemberDto channelMemberDto);


    void deleteChannel(UUID channelId);

    boolean isExistChannel(UUID channelId);
    boolean isChannelsMember(ChannelMemberDto channelMemberDto);














}
