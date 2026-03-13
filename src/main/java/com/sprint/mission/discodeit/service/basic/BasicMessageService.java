package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.messagedto.CreateMessageDto;
import com.sprint.mission.discodeit.dto.messagedto.MessageInfoDto;
import com.sprint.mission.discodeit.dto.messagedto.UpdateMessageDto;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BasicMessageService implements MessageService {


    private final MessageRepository messageRepository;
    private final BinaryContentRepository binaryContentRepository;


    @Override
    public MessageInfoDto create(CreateMessageDto createMessageDto) {

        Message message = new Message(


                createMessageDto.userId(),
                createMessageDto.channelId(),
                createMessageDto.content()

        );
        messageRepository.saveMessage(message);

        return infoDToEntity(message);
    }

    @Override
    public MessageInfoDto find(UUID messageId) {

        Message message = messageRepository.getMessage(messageId).orElseThrow();

        return infoDToEntity(message);
    }

    @Override
    public List<MessageInfoDto> findAllById(UUID channelId) {
        return messageRepository.getAllByChannelId(channelId)
                .stream()
                .map(this::infoDToEntity)
                .toList();
    }

    @Override
    public boolean updateMessage(UpdateMessageDto updateMessageDto) {

        Message message = messageRepository.getMessage(updateMessageDto.messageId()).orElseThrow();

        message.updateMessage(updateMessageDto.content());
        updateMessageDto.files().forEach(binaryContentRepository::saveBinaryContent);

        return true;
    }

    @Override
    public boolean deleteMessage(UUID messageId) {

        messageRepository.deleteMessage(messageId);
        BinaryContentRepository.deleteByMessageId();

        return true;



    }


    MessageInfoDto infoDToEntity (Message message){

        return new MessageInfoDto(

                message.getId(),
                message.getSenderId(),
                message.getChannelId(),
                message.getMessage(),
                BinaryContentRepository.getContentById(message.getId())
        );
    }










}