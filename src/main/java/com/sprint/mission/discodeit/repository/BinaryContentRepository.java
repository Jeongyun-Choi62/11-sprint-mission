package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.BinaryContent;

import java.util.List;
import java.util.Optional;

public interface BinaryContentRepository {

   BinaryContent saveBinaryContent(BinaryContent binaryContent);
   Optional<BinaryContent> getBinaryContent(String fileName);
   List<BinaryContent> getAllBinaryContent();
   boolean deleteBinaryContent(String fileName);
   boolean isExistBinaryContent(String fileName);








}
