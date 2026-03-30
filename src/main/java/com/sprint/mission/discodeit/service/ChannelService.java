package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.channeldto.*;

import java.util.List;
import java.util.UUID;

public interface ChannelService {

  PrivateChannelInfoDto createPrivate(CreatePrivateChannel createPrivateChannel);

  CreatedChannelInfo createPublic(CreatePublicChannel createPublicChannel);

  PrivateChannelInfoDto findPrivate(UUID channelId, UUID memberId);

  CreatedChannelInfo findPublic(UUID channelId);

  List<CreatedChannelInfo> findAllById(UUID userId);

  CreatedChannelInfo updateChannel(UpdateChannel updateChannel);


  void addMember(ChannelMemberDto channelMemberDto);

  void removeMember(ChannelMemberDto channelMemberDto);


  void deleteChannel(DeleteChannelDto deleteChannelDto);


}
