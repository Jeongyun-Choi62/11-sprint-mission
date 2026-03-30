package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.channeldto.*;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.exception.service.AlreadyExistException;
import com.sprint.mission.discodeit.exception.service.NonExistException;
import com.sprint.mission.discodeit.exception.service.WrongChannelTypeException;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicChannelService implements ChannelService {

  private final ChannelRepository channelRepository;
  private final ReadStatusRepository readStatusRepository;
  private final MessageRepository messageRepository;
  private final UserRepository userRepository;


  @Override
  public CreatedChannelInfo createPublic(CreatePublicChannel createPublicChannel) {

    Channel channel = new Channel(

        createPublicChannel.channelName(),
        Channel.ChannelType.PUBLIC,
        createPublicChannel.channelDescription()
    );

    //ReadStatus 생성(addMember 호출)
    createPublicChannel.membersId().stream()
        .filter(userRepository::isExistUser)
        .forEach(userId ->
            addMember(new ChannelMemberDto(userId, channel.getId()))
        );

    //default Message
    messageRepository.saveMessage(new Message(
        null,
        channel.getId(),
        "default message",
        new ArrayList<>()

    ));

    channelRepository.saveChannel(channel);
    return channelToPublicInfoDto(channel);


  }

  @Override
  public PrivateChannelInfoDto createPrivate(CreatePrivateChannel createPrivateChannel) {

    Channel channel = new Channel(
        null,
        Channel.ChannelType.PRIVATE,
        null
    );

    //ReadStatus 생성
    createPrivateChannel.membersId().stream()
        .filter(userRepository::isExistUser)
        .forEach(userId ->
            addMember(new ChannelMemberDto(userId, channel.getId()))
        );

    //default Message

    messageRepository.saveMessage(new Message(

        null,
        channel.getId(),
        "default message",
        new ArrayList<>()

    ));

    channelRepository.saveChannel(channel);

    return channelToPrivateInfoDto(channel);


  }


  @Override
  public PrivateChannelInfoDto findPrivate(UUID channelId, UUID memberId) {

    return channelToPrivateInfoDto(channelRepository.getChannel(channelId).orElseThrow());

  }

  @Override
  public CreatedChannelInfo findPublic(UUID channelId) {
    return channelToPublicInfoDto(channelRepository.getChannel(channelId).orElseThrow());
  }


  @Override
  public List<CreatedChannelInfo> findAllById(UUID userId) {

    return channelRepository.getAllChannel().stream()
        .filter(channel -> {

          ReadStatus readStatus = readStatusRepository.get(userId, channel.getId()).orElse(null);

          return (readStatus != null) && ((channel.getChannelType() == Channel.ChannelType.PRIVATE)
              || channel.getChannelType() == Channel.ChannelType.PUBLIC);

        })
        .map(channel -> {
          if (channel.getChannelType() == Channel.ChannelType.PUBLIC) {
            return channelToPublicInfoDto(channel);
          } else {
            return privateChannelToPublicInfoDto(channel);
          }
        })
        .toList();

  }

  @Override
  public CreatedChannelInfo updateChannel(UpdateChannel updateChannel) {

    Channel channel = channelRepository.getChannel(updateChannel.channelId()).orElseThrow();

    //public check
    if (channel.getChannelType() == Channel.ChannelType.PRIVATE) {
      throw new WrongChannelTypeException("Private 타입 채널은 변경할 수 없습니다.");
    }
    channel.updateChannelName(updateChannel.channelName());
    channel.updateChannelDescription(updateChannel.channelDescription());

    channelRepository.saveChannel(channel);

    return channelToPublicInfoDto(channel);


  }

  @Override
  public void addMember(ChannelMemberDto channelMemberDto) {
    if (readStatusRepository.isExist(channelMemberDto.memberId(), channelMemberDto.channelId())) {
      throw new AlreadyExistException("이미 존재하는 멤버입니다");
    }

    readStatusRepository.save(
        new ReadStatus(channelMemberDto.memberId(), channelMemberDto.channelId()));

  }

  @Override
  public void removeMember(ChannelMemberDto channelMemberDto) {
    if (!readStatusRepository.isExist(channelMemberDto.memberId(), channelMemberDto.channelId())) {
      throw new NonExistException("존재하지 않는 유저입니다.");
    }

    readStatusRepository.delete(channelMemberDto.memberId(), channelMemberDto.channelId());

  }

  @Override
  public void deleteChannel(DeleteChannelDto deleteChannelDto) {

    if (!channelRepository.isExistChannel(deleteChannelDto.channelId())) {
      throw new NonExistException("존재하는 채널이 아닙니다.");
    }

    readStatusRepository.getAllByChannelId(deleteChannelDto.channelId())
        .forEach(readStatus -> readStatusRepository.delete(readStatus.getUserId(),
            deleteChannelDto.channelId()));

    channelRepository.deleteChannel(deleteChannelDto.channelId());


  }

  public PrivateChannelInfoDto channelToPrivateInfoDto(Channel channel) {

    if (channel.getChannelType() != Channel.ChannelType.PRIVATE) {
      throw new WrongChannelTypeException("잘못된 채널 타입입니다");
    }

    return new PrivateChannelInfoDto(

        channel.getId(),
        Channel.ChannelType.PRIVATE,
        messageRepository.getLastMessagebyChannelId(channel.getId())
            .map(Message::getCreatedAt).orElseThrow()

    );

  }

  public CreatedChannelInfo channelToPublicInfoDto(Channel channel) {

    if (channel.getChannelType() != Channel.ChannelType.PUBLIC) {
      throw new WrongChannelTypeException("잘못된 채널 타입입니다");
    }

    return new CreatedChannelInfo(

        channel.getId(),
        channel.getChannelName(),
        Channel.ChannelType.PUBLIC,
        channel.getChannelDescription(),
        messageRepository.getLastMessagebyChannelId(channel.getId())
            .map(Message::getCreatedAt).orElseThrow()

    );


  }

  public CreatedChannelInfo privateChannelToPublicInfoDto(Channel channel) {

    if (channel.getChannelType() != Channel.ChannelType.PRIVATE) {
      throw new WrongChannelTypeException("잘못된 채널 타입입니다.");
    }

    return new CreatedChannelInfo(

        channel.getId(),
        channel.getChannelName(),
        Channel.ChannelType.PRIVATE,
        channel.getChannelDescription(),
        messageRepository.getLastMessagebyChannelId(channel.getId())
            .map(Message::getCreatedAt).orElseThrow()
    );
  }

}
