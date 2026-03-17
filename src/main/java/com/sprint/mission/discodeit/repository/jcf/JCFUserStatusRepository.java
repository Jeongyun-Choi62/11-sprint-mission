package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserStatusRepository;

import java.util.*;

public class JCFUserStatusRepository implements UserStatusRepository {

    private final Map<UUID, UserStatus> data;
    public JCFUserStatusRepository(){
        data = new HashMap<>();
    }

    @Override
    public UserStatus saveUserStatus(UserStatus userStatus) {

        data.put(userStatus.getId(), userStatus);
        return userStatus;

    }

    @Override
    public Optional<UserStatus> getUserStatus(UUID userId) {
        return Optional.ofNullable(data.get(userId));
    }

    @Override
    public List<UserStatus> getAllUserStatus() {
        return data.values().stream().toList();
    }

    @Override
    public boolean deleteUserStatus(UUID userId) {
        return data.remove(userId) != null;
    }

    @Override
    public boolean isExistUserStatus(UUID userId) {
        return data.containsKey(userId);
    }
}
