package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.ReadStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAReadStatusRepository extends JpaRepository<ReadStatus, UUID> {


  List<ReadStatus> findAllByUserId(UUID userId);

  List<ReadStatus> findAllByChannel_Id(UUID channelId);


  Boolean existsByUserIdAndChannelId(UUID userId, UUID channelId);


}
