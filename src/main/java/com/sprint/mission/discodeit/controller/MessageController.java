package com.sprint.mission.discodeit.controller;


import com.sprint.mission.discodeit.dto.messagedto.CreateMessageDto;
import com.sprint.mission.discodeit.dto.messagedto.MessageInfoDto;
import com.sprint.mission.discodeit.dto.messagedto.UpdateMessageDto;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;


  @PostMapping
  public ResponseEntity<MessageInfoDto> sendMessage(
      @RequestPart("messageCreateRequest") CreateMessageDto messageCreateRequest,
      @RequestPart List<MultipartFile> attachments) {

    MessageInfoDto messageInfoDto = messageService.create(messageCreateRequest, attachments);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(messageInfoDto.id())
        .toUri();
    return ResponseEntity.created(uri).body(messageInfoDto);


  }

  @GetMapping
  public ResponseEntity<List<MessageInfoDto>> getMessage(
      @RequestParam UUID channelId) {

    return ResponseEntity.status(HttpStatus.OK).body(messageService.findAllById(channelId));
  }

  @PatchMapping(value = "/{messageId}")
  public ResponseEntity<MessageInfoDto> updateMessage(
      UUID messageId,
      @RequestBody UpdateMessageDto updateMessageDto
  ) {
    messageService.updateMessage(messageId, updateMessageDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body(messageService.find(messageId));

  }

  @DeleteMapping(value = "/{messageId}")
  public ResponseEntity<Void> deleteMessage(@PathVariable UUID messageId) {

    messageService.deleteMessage(messageId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }


}
