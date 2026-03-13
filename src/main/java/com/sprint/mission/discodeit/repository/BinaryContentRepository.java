package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.BinaryContent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BinaryContentRepository {

   BinaryContent saveBinaryContent(BinaryContent binaryContent);
   Optional<BinaryContent> getBinaryContent(UUID binaryContentId);
   List<BinaryContent> getAllBinaryContent();
   boolean deleteBinaryContent(UUID binaryContentId);
   boolean isExistBinaryContent(UUID binaryContentId);








}
