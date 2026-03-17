package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.binarycontentdto.BinaryContentInfoDto;
import com.sprint.mission.discodeit.dto.binarycontentdto.CreateProfileImgDto;

import java.util.List;
import java.util.UUID;

public interface BinaryContentService {

    BinaryContentInfoDto create(BinaryContentInfoDto binaryContentInfoDto);
    BinaryContentInfoDto createProfileImg(CreateProfileImgDto createProfileImgDto);
    BinaryContentInfoDto find(UUID binaryContentId);
    List<BinaryContentInfoDto> findAllById(UUID userId);












}
