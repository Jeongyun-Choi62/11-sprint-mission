package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JPAChannelRepository extends JpaRepository<Channel, UUID> {

  @Query("SELECT c FROM Channel c, ReadStatus rs WHERE c.id = rs.channel.id AND rs.user.id = :userId")
  List<Channel> findAllByUser_Id(UUID userId);


}
