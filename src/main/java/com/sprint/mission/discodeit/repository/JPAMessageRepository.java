package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;

import com.sprint.mission.discodeit.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAMessageRepository extends JpaRepository<Message, UUID> {


  Page<Message> findAllByChannel_Id(UUID channelId, Pageable pageable);

  List<Message> findAllByAuthor(User author);

  Optional<Message> findTopByChannel_IdOrderByCreatedAtDesc(UUID channelId);


  void deleteById(UUID id);
}
