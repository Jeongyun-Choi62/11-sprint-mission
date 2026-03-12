package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.ReadStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReadStatusRepository {
    
    ReadStatus saveReadStatus(ReadStatus readStatus);
    Optional<ReadStatus> readReadStatus(UUID userId, UUID channelId);
    List<ReadStatus> readAllReadStatus();
    boolean deleteReadStatus(UUID userId, UUID channelId);
    boolean isExistReadStatus(UUID userId, UUID channelId);
    
    
}
