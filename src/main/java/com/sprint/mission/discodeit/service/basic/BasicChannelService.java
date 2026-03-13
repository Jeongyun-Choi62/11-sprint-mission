package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.channeldto.CreatePrivateChannelDto;
import com.sprint.mission.discodeit.dto.channeldto.CreatePublicChannelDto;
import com.sprint.mission.discodeit.dto.channeldto.FindPrivateChannelDto;
import com.sprint.mission.discodeit.dto.channeldto.FindPublicChannelDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicChannelService implements ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ReadStatusRepository readStatusRepository;


    @Override
    public void createPublic(CreatePublicChannelDto createPublicChannelDto) {

        Channel channel = new Channel(

                            createPublicChannelDto.channelName(),
                            createPublicChannelDto.ownerId(),
                            Channel.ChannelType.PUBLIC,
                            createPublicChannelDto.channelDescription()
                );

        channelRepository.saveChannel(channel);

        System.out.println("공개 채널 생성 완료!");

    }

    @Override
    public void createPrivate(CreatePrivateChannelDto createPrivateChannelDto) {

        Channel channel = new Channel(
                null,
                createPrivateChannelDto.ownerId(),
                Channel.ChannelType.PRIVATE,
                null
        );

        channelRepository.saveChannel(channel);



    }



    @Override
    public List<FindPublicChannelDto> findAllPublicById(UUID userId) {


        return channelRepository.getAllChannel().stream()
                .filter(channel -> channel.getChannelType() == Channel.ChannelType.PUBLIC)
                .map(channel-> new FindPublicChannelDto(

                        channel.getId(),
                        channel.getOwnerId(),
                        channel.getChannelName(),
                        channel.getChannelType(),
                        channel.getChannelDescription(),
                        readStatusRepository.getChannelsLasttime(userId)
                ));

    }

    @Override
    public List<FindPrivateChannelDto> findAllPrivateById(UUID memberId) {

        return channelRepository.getAllChannel().stream()
                .filter(channel -> channel.getChannelType() == Channel.ChannelType.PUBLIC)
                .map(channel-> new FindPublicChannelDto(


                        channel.getId(),
                        channel.getOwnerId(),
                        channel.getChannelType(),
                        getLastMessageTime(channel.getId())
                )
                );







    }






    //BasicService 에서는 사용 안함

    @Override
    public boolean isExistChannel(String channelId) {
        //채널 가져오기
        Channel channel = channelRepository.getChannel(channelId);
        return channel != null;

    }

    @Override
    public boolean isChannelsMember(String channelId, String memberId) {
        //채널 가져오기
        Channel channel = channelRepository.getChannel(channelId);
        //채널이 없거나 멤버가 없으면 false
        return channel != null && channel.getMembers().contains(memberId);
    }


    public Instant getLastMessageTime(UUID channelId){

        return null;



    }





}
